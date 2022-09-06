package com.sanhuo.ucode.schedule;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.container.ToolWindowComponentContainer;
import com.sanhuo.ucode.listener.CodeEditListener;
import com.sanhuo.ucode.toolwindow.CodeTimeToolWindow;
import com.sanhuo.ucode.util.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/16 19:14
 **/
@Slf4j
public class CodeTimeEventTimer {

    private static final ReentrantLock lock = new ReentrantLock();
    private LinkedList<DocumentEvent> eventList = new LinkedList<>();

    public void push(DocumentEvent event) {
        eventList.add(event);
    }

    public void run() {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                CodeTimeCache cache = (CodeTimeCache) ContainerManager.getContainer(CacheComponentContainer.class).getBean(CodeTimeCache.class);
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
                            ContainerManager.getContainer(ToolWindowComponentContainer.class).getBean(CodeTimeToolWindow.class).flush();

                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}


