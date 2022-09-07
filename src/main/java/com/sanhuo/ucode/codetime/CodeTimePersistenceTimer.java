package com.sanhuo.ucode.codetime;

import com.intellij.ide.plugins.PluginManager;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.codetime.CodeTimeCache;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.schedule.Schedule;
import com.sanhuo.ucode.schedule.ScheduleTask;
import com.sanhuo.ucode.util.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/16 19:14
 **/
@Slf4j
public class CodeTimePersistenceTimer extends Schedule {

    @Override
    protected ScheduleTask getTask() {
        return () ->{
            Cache cache = ContainerManager.getBean(CodeTimeCache.TODAY_CACHE);
            PersistenceUtil.getPersistence(CodeTimeCache.class).doPersistence(cache);
            PluginManager.getLogger().info("do persistence code time cache");
        };
    }

    @Override
    protected String getTaskName() {
        return "codeTimePersistenceTimer";
    }

    @Override
    protected Long delay() {
        return 60 * 1000l;
    }


}
