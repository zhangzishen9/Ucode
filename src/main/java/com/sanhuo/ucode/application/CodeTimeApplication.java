package com.sanhuo.ucode.application;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.container.CacheComponentContainer;
import com.sanhuo.ucode.container.ContainerManager;
import com.sanhuo.ucode.schedule.CodeTimeCacheFlushTimer;
import com.sanhuo.ucode.schedule.CodeTimeEventTimer;
import com.sanhuo.ucode.schedule.CodeTimePersistenceTimer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangzs
 * @description when plugin startup's move
 * @date 2022/8/11 14:18
 **/
public class CodeTimeApplication extends PreloadingActivity {
    //todo
    private static final List<Class<? extends Cache>> INIT_CACHE_LIST = ImmutableList.of(CodeTimeCache.class);

    @Override
    public void preload(@NotNull ProgressIndicator indicator) {
        CacheComponentContainer container = ContainerManager.getContainer(CacheComponentContainer.class);
        for (Class<? extends Cache> target : INIT_CACHE_LIST) {
            container.createBean(target);
        }
        CodeTimePersistenceTimer timer = new CodeTimePersistenceTimer();
        CodeTimeCacheFlushTimer timer1 = new CodeTimeCacheFlushTimer();
        CodeTimeEventTimer timer2 = new CodeTimeEventTimer();
        timer.run();
        timer1.run();
        timer2.run();

    }
}
