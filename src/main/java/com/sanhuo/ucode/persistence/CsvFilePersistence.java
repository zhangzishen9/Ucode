package com.sanhuo.ucode.persistence;

import com.alibaba.fastjson.JSONObject;
import com.sanhuo.ucode.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static com.sanhuo.ucode.constant.CodeTimeConstant.*;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 18:35
 **/
@Slf4j
public abstract class CsvFilePersistence<T extends Cache> implements Persistence<T> {
    protected static final String DIRECTORY = System.getProperty(USER_DIR);


    abstract String getPersistenceCsvFilename();

    @Override
    public void doPersistence(T cache) {
        if (cache == null) {
            return;
        }
        Map<String, Object> cacheMap = JSONObject.parseObject(JSONObject.toJSONString(cache), Map.class);
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Object> entry : cacheMap.entrySet()) {
            content.append(entry.getKey()).append(CSV_SPILT).append(entry.getValue()).append(CSV_LINE);
        }
        String file = this.getPersistenceCsvFilename();
        this.check(file);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.write(content.toString().getBytes(), fileOutputStream);
            log.info("persistence csv file : {} success ", file);
        } catch (Exception e) {
            log.error("persistence csv file: {} error : {}", file, e.getMessage());
        }
    }

    abstract T newInstance();

    abstract void initData(Map<String, String> csvDataMap, T cache);

    @Override
    public T dePersistence() {
        String path = this.getPersistenceCsvFilename();
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
            log.error("de persistence csv file: {} error : {}", file, e.getMessage());
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
                    log.error("mkdir directory[{}] error", directory.getAbsolutePath());
                }
            }
        }
    }
}
