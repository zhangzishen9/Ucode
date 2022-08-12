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
import com.sanhuo.ucode.application.ComponentManager;
import org.apache.tools.ant.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangzs
 * @description 输入监听
 * @date 2022/7/20 16:40
 **/
public class CodeEditListener
        implements DocumentListener {
    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        DocumentListener.super.documentChanged(event);
    }
    //
//    @Override
//    public void documentChanged(@NotNull DocumentEvent event) {
//        int oldLength = event.getOldLength();
//        int newLength = event.getNewLength();
////        FileDocumentManager instance = FileDocumentManager.getInstance();
//        System.out.println(1);
//        Document document = null;
//        ApplicationManager.getApplication().executeOnPooledThread(() -> {
//
//            FileDocumentManager instance = FileDocumentManager.getInstance();
//            if (instance != null) {
//                VirtualFile file = instance.getFile(document);
//                if (file != null && !file.isDirectory()) {
//                    Editor[] editors = EditorFactory.getInstance().getEditors(document);
//                    if (editors != null && editors.length > 0) {
//                        String fileName = file.getPath();
//                        Project project = editors[0].getProject();
//                        CodeTimeCache cache = ComponentManager.getBean(CodeTimeCache.class);
//                        String text = event.getNewFragment().toString();
//                        String oldText = event.getOldFragment().toString();
//
//                        int new_line_count = document.getLineCount();
//                        fileInfo.length = document.getTextLength();
//
//                        // this will give us the positive char change length
//                        int numKeystrokes = documentEvent.getNewLength();
//                        // this will tell us delete chars
//                        int numDeleteKeystrokes = documentEvent.getOldLength();
//
//                        // count the newline chars
//                        int linesAdded = getNewlineCount(text);
//                        int linesRemoved = getNewlineCount(oldText);
//
//                        // check if its an auto indent
//                        boolean hasAutoIndent = text.matches("^\\s{2,4}$") || TAB_PATTERN.matcher(text).find();
//                        boolean newLineAutoIndent = text.matches("^\n\\s{2,4}$") || NEW_LINE_TAB_PATTERN.matcher(text).find();
//
//                        // update the deletion keystrokes if there are lines removed
//                        numDeleteKeystrokes = numDeleteKeystrokes >= linesRemoved ? numDeleteKeystrokes - linesRemoved : numDeleteKeystrokes;
//
//                        // event updates
//                        if (newLineAutoIndent) {
//                            // it's a new line with auto-indent
//                            fileInfo.auto_indents += 1;
//                            fileInfo.linesAdded += 1;
//                        } else if (hasAutoIndent) {
//                            // it's an auto indent action
//                            fileInfo.auto_indents += 1;
//                        } else if (linesAdded == 1) {
//                            // it's a single new line action (single_adds)
//                            fileInfo.single_adds += 1;
//                            fileInfo.linesAdded += 1;
//                        } else if (linesAdded > 1) {
//                            // it's a multi line paste action (multi_adds)
//                            fileInfo.linesAdded += linesAdded;
//                            fileInfo.paste += 1;
//                            fileInfo.multi_adds += 1;
//                            fileInfo.is_net_change = true;
//                            fileInfo.characters_added += Math.abs(numKeystrokes - linesAdded);
//                        } else if (numDeleteKeystrokes > 0 && numKeystrokes > 0) {
//                            // it's a replacement
//                            fileInfo.replacements += 1;
//                            fileInfo.characters_added += numKeystrokes;
//                            fileInfo.characters_deleted += numDeleteKeystrokes;
//                        } else if (numKeystrokes > 1) {
//                            // pasted characters (multi_adds)
//                            fileInfo.paste += 1;
//                            fileInfo.multi_adds += 1;
//                            fileInfo.is_net_change = true;
//                            fileInfo.characters_added += numKeystrokes;
//                        } else if (numKeystrokes == 1) {
//                            // it's a single keystroke action (single_adds)
//                            fileInfo.add += 1;
//                            fileInfo.single_adds += 1;
//                            fileInfo.characters_added += 1;
//                        } else if (linesRemoved == 1) {
//                            // it's a single line deletion
//                            fileInfo.linesRemoved += 1;
//                            fileInfo.single_deletes += 1;
//                            fileInfo.characters_deleted += numDeleteKeystrokes;
//                        } else if (linesRemoved > 1) {
//                            // it's a multi line deletion and may contain characters
//                            fileInfo.characters_deleted += numDeleteKeystrokes;
//                            fileInfo.multi_deletes += 1;
//                            fileInfo.is_net_change = true;
//                            fileInfo.linesRemoved += linesRemoved;
//                        } else if (numDeleteKeystrokes == 1) {
//                            // it's a single character deletion action
//                            fileInfo.delete += 1;
//                            fileInfo.single_deletes += 1;
//                            fileInfo.characters_deleted += 1;
//                        } else if (numDeleteKeystrokes > 1) {
//                            // it's a multi character deletion action
//                            fileInfo.multi_deletes += 1;
//                            fileInfo.is_net_change = true;
//                            fileInfo.characters_deleted += numDeleteKeystrokes;
//                        }
//
//                        fileInfo.lines = new_line_count;
//                        fileInfo.keystrokes += 1;
//                        keystrokeCount.keystrokes += 1;
//                            }
//                        }
//                    }
//
//                }
//            }
//        });
//
//    }
}
