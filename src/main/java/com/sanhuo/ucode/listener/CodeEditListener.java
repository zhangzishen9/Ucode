package com.sanhuo.ucode.listener;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.sanhuo.ucode.schedule.CodeTimeEventTimer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description 输入监听
 * @date 2022/7/20 16:40
 **/
@Slf4j
public class CodeEditListener implements DocumentListener {
    private static final ReentrantLock lock = new ReentrantLock();

    private static CodeTimeEventTimer currentEventListener = null;


    private static Integer currentOffset = null;

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                if (currentEventListener == null) {
                    currentEventListener = new CodeTimeEventTimer();
                    currentEventListener.push(event);
                    currentOffset = event.getOffset();
                } else {
                    if (currentOffset.equals(event.getOffset())) {
                        currentEventListener.push(event);
                    } else {
                        currentEventListener.run();
                        currentEventListener = new CodeTimeEventTimer();
                        currentEventListener.push(event);
                        currentOffset = event.getOffset();
                    }
                }
            }

        } catch (Exception e) {
            log.error("code time listener error : {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }


}
