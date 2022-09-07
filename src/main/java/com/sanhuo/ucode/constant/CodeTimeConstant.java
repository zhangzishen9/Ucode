package com.sanhuo.ucode.constant;

import java.io.File;

/**
 * @author zhangzs
 * @description
 * @date 2022/8/11 14:15
 **/
public class CodeTimeConstant {
    /**
     * CodeTime Data Persistence File Name
     */
    public final static String CODE_TIME_FILE_TEMPLATE = "%s" + File.separator + "UCode" + File.separator + "UCode_CodeTime_%s.cache";


    /**
     * CodeTime Data Persistence File Suffix DATETIME FORMAT
     */
    public final static String CODE_TIME_FILE_SUFFIX_DATE_FORMAT = "yyyyMMdd";


    public static final String CSV_SPILT = ",";

    public static final String CSV_LINE = "\r\n";

    public static final String USER_DIR = "user.home";

}
