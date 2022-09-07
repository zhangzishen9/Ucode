package com.sanhuo.ucode.schedule;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.codetime.CodeTimeCache;
import com.sanhuo.ucode.codetime.CodeTimeToolWindow;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.util.LogUtils;
import com.sanhuo.ucode.util.PersistenceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 * @author zhangzs
 * @description 定时器
 * @date 2022/9/7 19:51
 **/
public abstract class Schedule {
    /**
     * @return
     */
    protected abstract ScheduleTask getTask();

    /**
     * 任务名
     *
     * @return
     */
    protected abstract String getTaskName();

    /**
     * 间隔
     *
     * @return
     */
    protected abstract Long delay();

    public void run() {
        TimerTask task = new TimerTask() {
            public void run() {
                getTask().run();
            }
        };
        Timer schedule = new Timer(getTaskName());
        schedule.schedule(task, delay(), delay());
    }


}
