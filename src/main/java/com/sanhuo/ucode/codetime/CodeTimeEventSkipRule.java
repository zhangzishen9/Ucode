package com.sanhuo.ucode.codetime;

import com.intellij.openapi.editor.event.DocumentEvent;

/**
 * @author zhangzs
 * @description
 * @date 2022/9/13 16:15
 **/
public class CodeTimeEventSkipRule {

    public static boolean isSkip(DocumentEvent event){
        String text = event.getNewFragment().toString();
        String oldText = event.getOldFragment().toString();
        boolean isMulti = text.contains("\r") || text.contains("\n") || oldText.contains("\n") || oldText.contains("\r");
        boolean isImport = text.contains("import") || oldText.contains("import");
        boolean isAutoCompletion = text.endsWith(".") || oldText.endsWith(".");
        return isMulti || isImport || isAutoCompletion;
    }
}
