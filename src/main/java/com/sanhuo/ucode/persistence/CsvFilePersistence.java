package com.sanhuo.ucode.persistence;

import com.intellij.ide.plugins.PluginManager;
import com.sampullara.cli.Args;
import com.sanhuo.ucode.cache.Cache;
import com.sanhuo.ucode.codetime.CodeTimeCache;
import com.sanhuo.ucode.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.sanhuo.ucode.constant.CodeTimeConstant.*;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 18:35
 **/
public abstract class CsvFilePersistence<T extends Cache> implements Persistence<T> {
    protected static final String DIRECTORY = System.getProperty(USER_DIR);

    public static void main(String[] args) {
        CodeTimeCache cache = new CodeTimeCache();
        Map<String, Object> cacheMap = toMap(cache);
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            content.append(entry.getKey()).append(CSV_SPILT).append(entry.getValue()).append(CSV_LINE);
        }
        String file = "/Users/zoe/Downloads/text.txt";
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.write(content.toString().getBytes(), fileOutputStream);
            LogUtils.info("persistence csv file : {} success ", file);
        } catch (Exception e) {
            LogUtils.error("persistence csv file: {} error : {}", file, e.getMessage());
        }
    }

    protected abstract String getPersistenceCsvFilename(Object... args);

    @Override
    public void doPersistence(T cache, Object... args) {
        if (cache == null) {
            return;
        }
        Map<String, Object> cacheMap = toMap(cache);
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            content.append(entry.getKey()).append(CSV_SPILT).append(entry.getValue()).append(CSV_LINE);
        }
        String file = this.getPersistenceCsvFilename(args);
        this.check(file);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.write(content.toString().getBytes(), fileOutputStream);
            LogUtils.info("persistence csv file : {} success ", file);
        } catch (Exception e) {
            LogUtils.error("persistence csv file: {} error : {}", file, e.getMessage());
        }
    }

    private static Map<String, Object> toMap(Cache cache) {
        Map<String, Object> result = new HashMap<>();
        Class<? extends Cache> target = cache.getClass();
        Field[] fields = target.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(cache);
                result.put(field.getName(), value);
            } catch (Exception e) {
                LogUtils.error("get Value error {}", e.getMessage());
            }
        }
        return result;
    }

    protected abstract T newInstance();

    protected abstract void initData(Map<String, String> csvDataMap, T cache);

    @Override
    public T dePersistence(Object... args) {
        String path = this.getPersistenceCsvFilename(args);
        this.check(path);
        File file = new File(path);
        T cache = this.newInstance();
        if (!file.exists()) {
            return cache;
        }
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            Map<String, String> csvDataMap = new HashMap<>();
            br.lines().forEach(line -> {
                String[] values = line.split(",");
                csvDataMap.put(values[0], values[1]);
            });
            this.initData(csvDataMap, cache);
        } catch (Exception e) {
            LogUtils.error("de persistence csv file: {} error : {}", e, file.getAbsolutePath());
        }
        return cache;
    }


    private void check(String file) {
        int index = file.lastIndexOf(File.separator);
        if (index != -1) {
            File directory = new File(file.substring(0, index));
            if (!directory.exists() || !directory.isDirectory()) {
                boolean flag = directory.mkdirs();
                if (!flag) {
                    PluginManager.getLogger().error(String.format("mkdir directory[%s] error", directory.getAbsolutePath()));
                }
            }
        }
    }
}
