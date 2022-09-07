//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sanhuo.ucode.util;

import com.intellij.ide.plugins.PluginManager;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanUtils {
    private final static Map<Class, List<PropertyDescriptor>> beanPropertyDescriptorMap = new ConcurrentHashMap<>();

    public static final List<PropertyDescriptor> getPropertyDescriptors(Class target) {
        try {
            if (!beanPropertyDescriptorMap.containsKey(target)) {
                BeanInfo beanInfo = Introspector.getBeanInfo(target);
                beanPropertyDescriptorMap.put(target, new ArrayList<>(Arrays.asList(beanInfo.getPropertyDescriptors())));
            }
        } catch (Exception e) {
            PluginManager.getLogger().error(String.format("get class : %s properties error: %s", target.getName(), e.getMessage()));
        }
        return beanPropertyDescriptorMap.getOrDefault(target, new ArrayList<>());
    }
}
