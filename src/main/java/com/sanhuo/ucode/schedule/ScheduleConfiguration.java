package com.sanhuo.ucode.schedule;

import com.google.common.collect.ImmutableList;
import com.sanhuo.ucode.codetime.CodeTimeCacheFlushTimer;
import com.sanhuo.ucode.codetime.CodeTimePersistenceTimer;
import com.sanhuo.ucode.util.LogUtils;

import java.util.List;

/**
 * @author zhangzs
 * @description
 * @date 2022/9/7 19:57
 **/
public class ScheduleConfiguration {
    //todo
    private static final List<Class<? extends Schedule>> TIMER_LIST = ImmutableList.of(
            CodeTimeCacheFlushTimer.class,
            CodeTimePersistenceTimer.class);


    public static void start() {
        try {
            for (Class<? extends Schedule> target : TIMER_LIST) {
                Schedule timer = target.getDeclaredConstructor().newInstance();
                timer.run();
            }
        } catch (Exception e) {
            LogUtils.error("start timer error :{}", e, e.getMessage());
        }
    }
}
