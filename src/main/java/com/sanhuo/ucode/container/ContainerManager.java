package com.sanhuo.ucode.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:55
 **/
public class ContainerManager {
    private static Map<Class<? extends ComponentContainer>, ComponentContainer> containerMap = new ConcurrentHashMap<>();

    static {
        register(CacheComponentContainer.class, new CacheComponentContainer());
        register(ToolWindowComponentContainer.class, new ToolWindowComponentContainer());
    }

    public static void register(Class<? extends ComponentContainer> containerClass, ComponentContainer container) {
        containerMap.put(containerClass, container);
    }

    public static <T extends ComponentContainer> T getContainer(Class<T> target) {
        return (T) containerMap.get(target);
    }
}
