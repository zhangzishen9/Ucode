package com.sanhuo.ucode.listener;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.sanhuo.ucode.codetime.CodeTimeEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description 输入监听
 * @date 2022/7/20 16:40
 **/
@Slf4j
public class CodeEditListener implements DocumentListener {
    private static final ReentrantLock lock = new ReentrantLock();

    private static CodeTimeEventHandler currentEventListener = null;


    private static Integer currentOffset = null;

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        if (isMultiLine(event)) {
            return;
        }
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                if (currentEventListener == null) {
                    currentEventListener = new CodeTimeEventHandler();
                    currentEventListener.push(event);
                    currentOffset = event.getOffset();
                } else {
                    if (currentOffset.equals(event.getOffset())) {
                        currentEventListener.push(event);
                    } else {
                        currentEventListener.run();
                        currentEventListener = new CodeTimeEventHandler();
                        currentEventListener.push(event);
                        currentOffset = event.getOffset();
                    }
                }
            }

        } catch (Exception e) {
            PluginManager.getLogger().error("code time listener error : {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }


    /**
     * 一次变动多行的不计算
     * @param documentEvent
     * @return
     */
    private boolean isMultiLine(DocumentEvent documentEvent) {
        String text = documentEvent.getNewFragment() != null ? documentEvent.getNewFragment().toString() : "";
        String oldText = documentEvent.getOldFragment() != null ? documentEvent.getOldFragment().toString() : "";
        return text.contains("\r") || text.contains("\n") || oldText.contains("\n") || oldText.contains("\r");
    }


}
