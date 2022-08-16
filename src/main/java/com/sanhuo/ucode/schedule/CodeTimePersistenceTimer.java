package com.sanhuo.ucode.schedule;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;
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
public class CodeTimePersistenceTimer {
    //todo
    public void run() {
        TimerTask task = new TimerTask() {
            public void run() {
                Cache cache = ContainerManager.getContainer(CacheComponentContainer.class).getBean(CodeTimeCache.class);
                PersistenceUtil.getPersistence(CodeTimeCache.class).doPersistence(cache);
                log.info("do persistence code time cache");
            }
        };
        Timer codeTimePersistenceTimer = new Timer("codeTimePersistenceTimer");
        //10s per time
        codeTimePersistenceTimer.schedule(task, 60 * 1000, 60 * 1000);
    }

}
