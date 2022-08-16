package com.sanhuo.ucode.container;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:32
 **/
@Slf4j
public abstract class AbstractComponentContainer<T> implements ComponentContainer<T> {

    protected final Map<Class, Object> componentMap = new ConcurrentHashMap<>();

    private static final ReentrantLock lock = new ReentrantLock();


    @Override
    public void createBean(Class<? extends T> beanClass) {
        try {
            lock.lock();
            if (componentMap.containsKey(beanClass)) {
                return;
            }
            this.putBean(beanClass, this.createAndReturnBean(beanClass));
        } catch (Exception e) {
            log.error("create bean {} error:{}", beanClass.getName(), e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    abstract protected  T createAndReturnBean(Class<? extends T> beanClass) throws Exception;


    @Override
    public  void putBean(Class<? extends T> beanClas, T bean) {
        this.putBeanCheck(beanClas, bean);
        componentMap.put(beanClas, bean);
    }

    /**
     * do something before put bean
     *
     * @param beanClas
     * @param bean
     */
    abstract protected  void putBeanCheck(Class<? extends T> beanClas, T bean);

    @Override
    public T getBean(Class<? extends T> beanClass) {
        try {
            lock.lock();
            if (!componentMap.containsKey(beanClass)) {
                this.createBean(beanClass);
            }
            return (T) componentMap.get(beanClass);
        } finally {
            lock.unlock();
        }
    }
}
