package com.sanhuo.ucode.persistence;

import com.sanhuo.ucode.cache.Cache;

/**
 * @author zhangzs
 * @description to persistend #{@link com.sanhuo.ucode.cache.CodeTimeCache}
 * @date 2022/7/22 17:13
 **/
public interface Persistence<T extends Cache> {

    /**
     * do Persistence
     */
    void doPersistence(T cache,Object...args);

    /**
     * do dePersistence
     */
    T dePersistence(Object...args);
}
