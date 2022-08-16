package com.sanhuo.ucode.toolwindow;

import com.intellij.openapi.wm.ToolWindow;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;

import javax.swing.*;

/**
 * @author zhangzs
 * @description 右侧菜单
 * @date 2022/8/10 17:04
 **/
public class CodeTimeToolWindow extends AbstractToolWindow {
    private JPanel content;
    private JLabel TodayCodeTimeTitle;
    private JLabel TodayActiveCodeTimeTitle;
    private JLabel IncreaseCodeNumberTitle;
    private JLabel DecreaseCodeNumberTitle;
    private JLabel TodayCodeTime;
    private JLabel TodayActiveCodeTime;
    private JLabel IncreaseCodeNumber;
    private JLabel DecreaseCodeNumber;

    public CodeTimeToolWindow(ToolWindow toolWindow) {
        super(toolWindow);
        this.flush();
    }


    @Override
    public void flush() {
        CodeTimeCache codeTimeCache = (CodeTimeCache) ContainerManager.getContainer(CacheComponentContainer.class).getBean(CodeTimeCache.class);
        TodayCodeTime.setText(codeTimeCache.getTodayCodeTime());
        TodayActiveCodeTime.setText(codeTimeCache.getTodayActiveCodeTime());
        IncreaseCodeNumber.setText(codeTimeCache.getIncreaseCodeNumber() + "");
        DecreaseCodeNumber.setText(codeTimeCache.getDecreaseCodeNumber() + "");
    }

    @Override
    JPanel getContent() {
        return content;
    }
}
