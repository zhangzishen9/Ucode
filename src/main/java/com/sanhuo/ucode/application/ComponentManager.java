package com.sanhuo.ucode.application;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.persistence.Persistence;
import com.sanhuo.ucode.persistence.PersistenceFor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 16:21
 **/
@Slf4j
public class ComponentManager {
    private static final Map<Class<? extends Cache>, Cache> cacheMap = new ConcurrentHashMap<>();

    private static final ReentrantLock lock = new ReentrantLock();

    public static <T extends Cache> T getBean(Class<T> beanClass) {
        try {
            lock.lock();
            if (!cacheMap.containsKey(beanClass)) {
                createBean(beanClass);
            }
            return (T) cacheMap.get(beanClass);
        } finally {
            lock.unlock();
        }
    }


    public static <T extends Cache> void createBean(Class<T> beanClass) {
        try {
            lock.lock();
            PersistenceFor annotation = beanClass.getAnnotation(PersistenceFor.class);
            try {
                Persistence<T> persistence = annotation.value().getDeclaredConstructor().newInstance();
                cacheMap.put(beanClass, persistence.dePersistence());
            } catch (Exception e) {
                log.error("create bean {} error:{}", beanClass.getName(), e.getMessage());

            }
        } finally {
            lock.unlock();
        }
    }
}
