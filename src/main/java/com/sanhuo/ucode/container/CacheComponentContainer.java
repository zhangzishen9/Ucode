package com.sanhuo.ucode.container;

import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.persistence.Persistence;
import com.sanhuo.ucode.persistence.PersistenceFor;
import com.sanhuo.ucode.util.PersistenceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 16:21
 **/
@Slf4j
public class CacheComponentContainer extends AbstractComponentContainer<Cache> {
    @Override
    protected Cache createAndReturnBean(Class<? extends Cache> beanClass) throws Exception {
        return PersistenceUtil.getPersistence(beanClass).dePersistence();
    }

    @Override
    protected void putBeanCheck(Class<? extends Cache> beanClas, Cache bean) {

    }
}
