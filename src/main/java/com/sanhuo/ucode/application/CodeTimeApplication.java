package com.sanhuo.ucode.application;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.schedule.CodeTimeCacheFlushTimer;
import com.sanhuo.ucode.schedule.CodeTimePersistenceTimer;
import com.sanhuo.ucode.util.DateUtils;
import com.sanhuo.ucode.util.PersistenceUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 * @author zhangzs
 * @description when plugin startup's move
 * @date 2022/8/11 14:18
 **/
public class CodeTimeApplication extends PreloadingActivity {

    @Override
    public void preload(@NotNull ProgressIndicator indicator) {
        ContainerManager.putBean(CodeTimeCache.TODAY_CACHE, PersistenceUtil.getPersistence(CodeTimeCache.class).dePersistence());
        ContainerManager.putBean(CodeTimeCache.YESTERDAY_CACHE, PersistenceUtil.getPersistence(CodeTimeCache.class).dePersistence(DateUtils.plusDay(new Date(), -1)));
        CodeTimePersistenceTimer timer = new CodeTimePersistenceTimer();
        CodeTimeCacheFlushTimer timer1 = new CodeTimeCacheFlushTimer();
        timer.run();
        timer1.run();
    }
}
