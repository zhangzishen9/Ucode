package com.sanhuo.ucode.toolwindow;

import com.intellij.openapi.wm.ToolWindow;
import com.sanhuo.ucode.application.ComponentManager;
import com.sanhuo.ucode.cache.CodeTimeCache;

import javax.swing.*;
import java.util.Calendar;

/**
 * @author zhangzs
 * @description 右侧菜单
 * @date 2022/8/10 17:04
 **/
public class CodeTimeToolWindow {

    private JPanel CodeTimeToolWindowContent;
    private JLabel TodayCodeTimeTitle;
    private JLabel TodayActiveCodeTimeTitle;
    private JLabel IncreaseCodeNumberTitle;
    private JLabel DecreaseCodeNumberTitle;
    private JLabel TodayCodeTime;
    private JLabel TodayActiveCodeTime;
    private JLabel IncreaseCodeNumber;
    private JLabel DecreaseCodeNumber;

    public CodeTimeToolWindow(ToolWindow toolWindow) {
        this.flush();
    }

    public void flush() {
        CodeTimeCache codeTimeCache = ComponentManager.getBean(CodeTimeCache.class);
        TodayCodeTime.setText(codeTimeCache.getTodayCodeTime());
        TodayActiveCodeTime.setText(codeTimeCache.getTodayActiveCodeTime());
        IncreaseCodeNumber.setText(codeTimeCache.getIncreaseCodeNumber() + "");
        DecreaseCodeNumber.setText(codeTimeCache.getDecreaseCodeNumber() + "");
    }

    public JPanel getContent() {
        return CodeTimeToolWindowContent;
    }


}
