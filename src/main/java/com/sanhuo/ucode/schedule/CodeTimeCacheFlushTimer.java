package com.sanhuo.ucode.schedule;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.util.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/16 19:14
 **/
@Slf4j
public class CodeTimeCacheFlushTimer {
    public void run() {
        TimerTask task = new TimerTask() {
            public void run() {
                CodeTimeCache cache = (CodeTimeCache) ContainerManager.getContainer(CacheComponentContainer.class).getBean(CodeTimeCache.class);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if (!sdf.format(cache.getCacheDate()).equals(sdf.format(new Date()))) {
                        Cache newCache = PersistenceUtil.getPersistence(CodeTimeCache.class).dePersistence();
                        ContainerManager.getContainer(CacheComponentContainer.class).putBean(CodeTimeCache.class, newCache);
                    }
                } catch (Exception e) {
                    log.info("flush error,e:{}", e.getMessage());
                }

            }
        };
        Timer codeTimeFlushTimer = new Timer("codeTimeFlushTimer");
        //10s per time
        codeTimeFlushTimer.schedule(task, 60 * 1000, 60 * 1000);
    }

}
