package com.sanhuo.ucode.helper;

import com.alibaba.fastjson.JSONObject;
import com.intellij.ide.plugins.PluginManager;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/12 17:30
 **/
@Slf4j
public class PropertiesHelper<T extends Cache> {

    private final Class<T> target;

    private final Map<String, Method> writeMethodMap = new ConcurrentHashMap<>();

    private final Map<String, Method> readMethodMap = new ConcurrentHashMap<>();

    private final Map<String, Class> typeMap = new ConcurrentHashMap<>();


    public PropertiesHelper(Class<T> target) {
        this.target = target;
        List<PropertyDescriptor> propertyDescriptors = BeanUtils.getPropertyDescriptors(target);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if("class".equals(propertyDescriptor.getName())){
                continue;
            }
            writeMethodMap.put(propertyDescriptor.getName(), propertyDescriptor.getWriteMethod());
            readMethodMap.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod());
            typeMap.put(propertyDescriptor.getName(), propertyDescriptor.getPropertyType());
        }

    }

    /**
     * only support simple type copy ,like Integer/String/Double
     *
     * @param sourceDataMap
     * @param target
     */
    public void copyProperties(Map<String, String> sourceDataMap, T target) {
        String property = "";
        try {
            for (Map.Entry<String, String> entry : sourceDataMap.entrySet()) {
                property = entry.getKey();
                if (writeMethodMap.containsKey(property)) {
                    Class type = typeMap.get(property);
                    Object value = JSONObject.parseObject(entry.getValue(), type);
                    Method writeMethod = writeMethodMap.get(property);
                    writeMethod.setAccessible(true);
                    writeMethod.invoke(target, value);
                }
            }
        } catch (Exception e) {
             PluginManager.getLogger().error("copy property[{}] error : {}", property, e.getMessage());
        }
    }
}
