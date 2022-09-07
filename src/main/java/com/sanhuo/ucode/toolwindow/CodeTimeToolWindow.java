package com.sanhuo.ucode.toolwindow;

import com.intellij.openapi.wm.ToolWindow;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.ContainerManager;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangzs
 * @description 右侧菜单
 * @date 2022/8/10 17:04
 **/
public class CodeTimeToolWindow extends AbstractToolWindow {
    private JPanel content;
    private JLabel IncreaseCodeNumberTitle;
    private JLabel DecreaseCodeNumberTitle;
    private JLabel IncreaseCodeNumber;
    private JLabel DecreaseCodeNumber;
    private JLabel YesterdayIncreaseCodeNumber;
    private JLabel YesterdayDecreaseCodeNumber;
    private JLabel Today;

    public CodeTimeToolWindow(ToolWindow toolWindow) {
        super(toolWindow);
        this.flush();
    }


    @Override
    public void flush() {
        CodeTimeCache codeTimeCache = (CodeTimeCache) ContainerManager.getBean(CodeTimeCache.TODAY_CACHE);
        CodeTimeCache yesterdayCache = (CodeTimeCache) ContainerManager.getBean(CodeTimeCache.YESTERDAY_CACHE);
        IncreaseCodeNumber.setText(codeTimeCache.getIncreaseCodeNumber() + "");
        DecreaseCodeNumber.setText(codeTimeCache.getDecreaseCodeNumber() + "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        YesterdayDecreaseCodeNumber.setText(yesterdayCache != null ? yesterdayCache.getIncreaseCodeNumber() + "" : "0");
        YesterdayIncreaseCodeNumber.setText(yesterdayCache != null ? yesterdayCache.getDecreaseCodeNumber() + "" : "0");
        Today.setText(sdf.format(codeTimeCache.getCacheDate() != null ? codeTimeCache.getCacheDate() : new Date()));
    }

    @Override
    JPanel getContent() {
        return content;
    }
}
