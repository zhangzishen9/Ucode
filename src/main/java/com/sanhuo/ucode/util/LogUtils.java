package com.sanhuo.ucode.util;

import com.intellij.ide.plugins.PluginManager;

/**
 * @author zhangzs
 * @description
 * @date 2022/9/7 12:05
 **/
public class LogUtils {

    public static void info(String template, String... args) {
        PluginManager.getLogger().info(String.format(template.replace("{}", "%s"), args));

    }


    public static void error(String template, String... args) {
        PluginManager.getLogger().error(String.format(template.replace("{}", "%s"), args));
    }

    public static void error(String template, Exception e) {
        error(template, e);
    }

    public static void error(String template, Exception e, String... args) {
        e.printStackTrace();
        error(template,args);
    }
}
