package com.sanhuo.ucode.persistence;

import com.sanhuo.ucode.cache.CodeTimeCache;
import com.sanhuo.ucode.helper.PropertiesHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.sanhuo.ucode.constant.CodeTimeConstant.*;

/**
 * @author zhangzs
 * @description persistence for file
 * @date 2022/8/11 15:52
 **/
public class CodeTimeCachePersistence extends CsvFilePersistence<CodeTimeCache> {

    @Override
    String getPersistenceCsvFilename(Object... args) {
        Date date = args != null && args.length > 1 && args[1] instanceof Date ? (Date) args[1] : new Date();
        return String.format(CODE_TIME_FILE_TEMPLATE, DIRECTORY, new SimpleDateFormat(CODE_TIME_FILE_SUFFIX_DATE_FORMAT).format(date));
    }

    @Override
    CodeTimeCache newInstance() {
        return new CodeTimeCache();
    }

    @Override
    void initData(Map<String, String> csvDataMap, CodeTimeCache codeTimeCache) {
        PropertiesHelper helper = new PropertiesHelper(CodeTimeCache.class);
        helper.copyProperties(csvDataMap, codeTimeCache);
        codeTimeCache.setCacheDate(new Date());
    }
}
