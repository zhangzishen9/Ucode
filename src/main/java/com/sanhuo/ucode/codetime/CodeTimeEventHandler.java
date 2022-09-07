package com.sanhuo.ucode.codetime;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.sanhuo.ucode.container.ContainerManager;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/16 19:14
 **/
@Slf4j
public class CodeTimeEventHandler {

    private static final ReentrantLock lock = new ReentrantLock();
    private LinkedList<DocumentEvent> eventList = new LinkedList<>();

    public void push(DocumentEvent event) {
        eventList.add(event);
    }

    public void run() {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                CodeTimeCache cache = ContainerManager.getBean(CodeTimeCache.TODAY_CACHE);
                DocumentEvent event = eventList.pollLast();
                Document document = event.getDocument();
                FileDocumentManager instance = FileDocumentManager.getInstance();
                if (instance != null) {
                    VirtualFile file = instance.getFile(document);
                    if (file != null && !file.isDirectory()) {
                        Editor[] editors = EditorFactory.getInstance().getEditors(document);
                        if (editors != null && editors.length > 0) {
                            String text = event.getNewFragment().toString().trim();
                            String oldText = event.getOldFragment().toString().trim();

                            int decreaseCodeNumber = oldText.length();
                            int increaseCodeNumber = text.length();

                            if (decreaseCodeNumber > 0) {
                                cache.setDecreaseCodeNumber(cache.getDecreaseCodeNumber() + decreaseCodeNumber);
                            }
                            if (increaseCodeNumber > 0) {
                                cache.setIncreaseCodeNumber(cache.getIncreaseCodeNumber() + increaseCodeNumber);
                            }
                            ContainerManager.getBean(CodeTimeToolWindow.class).flush();

                        }
                    }
                }
            }
        } catch (Exception e) {
            PluginManager.getLogger().error("error: {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}


