package com.sanhuo.ucode.toolwindow;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:03
 **/
public abstract class AbstractToolWindow {


    public AbstractToolWindow(ToolWindow toolWindow) {

    }


    /**
     * flush tool window data
     */
    public abstract  void flush();

    public abstract JPanel getContent();

}
