package com.sanhuo.ucode.application;

import com.google.common.collect.ImmutableList;
import com.intellij.openapi.application.PreloadingActivity;
import com.intellij.openapi.progress.ProgressIndicator;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.cache.CodeTimeCache;
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
        for (Class<? extends Cache> target : INIT_CACHE_LIST) {
            ComponentManager.createBean(target);
        }
    }
}
