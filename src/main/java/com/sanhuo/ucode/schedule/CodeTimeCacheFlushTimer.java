package com.sanhuo.ucode.schedule;

import com.intellij.ide.plugins.PluginManager;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.toolwindow.CodeTimeToolWindow;
import com.sanhuo.ucode.util.LogUtils;
import com.sanhuo.ucode.util.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
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
                CodeTimeCache cache = (CodeTimeCache) ContainerManager.getBean(CodeTimeCache.TODAY_CACHE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if (cache.getCacheDate() != null) {
                        if (!sdf.format(cache.getCacheDate()).equals(sdf.format(new Date()))) {
                            Cache newCache = PersistenceUtil.getPersistence(CodeTimeCache.class).dePersistence();
                            ContainerManager.putBean(CodeTimeCache.TODAY_CACHE, newCache);
                            ContainerManager.putBean(CodeTimeCache.YESTERDAY_CACHE, cache);
                            ContainerManager.getBean(CodeTimeToolWindow.class).flush();
                        }
                    }
                } catch (Exception e) {
                    LogUtils.error("flush error,e:{}", e.getMessage());
                }

            }
        };
        Timer codeTimeFlushTimer = new Timer("codeTimeFlushTimer");
        //10s per time
        codeTimeFlushTimer.schedule(task, 60 * 1000, 60 * 1000);
    }

}
