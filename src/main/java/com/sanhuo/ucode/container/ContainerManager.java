package com.sanhuo.ucode.container;

import com.intellij.ide.plugins.PluginManager;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:55
 **/
public class ContainerManager {

    private static Map<String, Object> componentMap = new ConcurrentHashMap<>();


    private static final ReentrantLock lock = new ReentrantLock();


    public static <T> void putBean(Class<? extends T> beanClas, T bean) {
        putBean(getDefaultName(beanClas), bean);
    }
    
    public static <T> void putBean(String name, T bean) {
        componentMap.put(name, bean);
    }

    public static <T> T getBean(String name) {
        return (T) componentMap.get(name);
    }

    public static <T> T getBean(Class<? extends T> beanClass) {
        String name = getDefaultName(beanClass);
        if (!componentMap.containsKey(name)) {
            return null;
        }
        return (T) componentMap.get(name);
    }

    private static String getDefaultName(Class beanClass) {
        String defaultName = beanClass.getSimpleName();
        defaultName = defaultName.substring(0, 1).toLowerCase(Locale.ROOT) + defaultName.substring(1);
        return defaultName;
    }
}
