package com.sanhuo.ucode.util;

import com.intellij.ide.plugins.PluginManager;
import com.sanhuo.ucode.persistence.Persistence;
import com.sanhuo.ucode.persistence.PersistenceFor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/16 19:23
 **/
@Slf4j
public class PersistenceUtil {

    public static Persistence getPersistence(Class target) {
        PersistenceFor persistenceFor = (PersistenceFor) target.getAnnotation(PersistenceFor.class);
        Persistence persistence = null;
        try {
            persistence = persistenceFor.value().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            //todo
             PluginManager.getLogger().error("get persistence error:{}", e.getMessage());
        }
        return persistence;
    }
}
