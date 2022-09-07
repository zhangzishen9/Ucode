package com.sanhuo.ucode.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangzs
 * @description
 * @date 2022/9/7 17:09
 **/
public class DateUtils {


    public static Date plusDay(Date target, int plusDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(target);
        calendar.add(Calendar.DAY_OF_MONTH, plusDay);
        return calendar.getTime();
    }
}
