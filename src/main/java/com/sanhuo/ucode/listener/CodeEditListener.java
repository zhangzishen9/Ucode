package com.sanhuo.ucode.listener;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.container.ToolWindowComponentContainer;
import com.sanhuo.ucode.toolwindow.AbstractToolWindow;
import com.sanhuo.ucode.toolwindow.CodeTimeToolWindow;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * @author zhangzs
 * @description 输入监听
 * @date 2022/7/20 16:40
 **/
public class CodeEditListener implements DocumentListener {

    //    @Override
//    public void documentChanged(@NotNull DocumentEvent event) {
//        DocumentListener.super.documentChanged(event);
//    }
    //

    private static final Pattern NEW_LINE_TAB_PATTERN = Pattern.compile("\n\t");
    private static final Pattern TAB_PATTERN = Pattern.compile("\t");

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        int oldLength = event.getOldLength();
        int newLength = event.getNewLength();
        System.out.println(1);
        Document document = event.getDocument();
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            CodeTimeCache cache = (CodeTimeCache) ContainerManager.getContainer(CacheComponentContainer.class).getBean(CodeTimeCache.class);
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
        });

    }


    public static int getNewlineCount(String text) {
        if (StringUtils.isBlank(text)) {
            return 0;
        }
        return text.split("[\n|\r]").length;
    }


}
