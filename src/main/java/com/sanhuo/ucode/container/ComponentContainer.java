package com.sanhuo.ucode.container;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/15 11:07
 **/
public interface ComponentContainer<T> {

    void createBean(Class<? extends T> beanClass);

    void putBean(Class<? extends T> beanClas, T bean);

     T getBean(Class<? extends T> beanClass);
}
