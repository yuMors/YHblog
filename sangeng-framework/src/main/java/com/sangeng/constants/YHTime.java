package com.sangeng.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 私有定义
 * 时间类
 *
 * 用static可以直接类名.方法名 直接调用
 * 不用的话只能new一个对象 用对象.方法名的方式调用
 *
 * 静态方法与静态变量好处：
 * 1. 属于类级别，无需创建对象就即可直接使用，使用方便。
 * 2. 全局唯一，内存中唯一，静态变量可以唯一标识某些状态。
 * 3. 类加载时候初始化，常驻在内存，调用快捷方便。
 */
public class YHTime {
    //先定义这三种格式 H左边表示是否汉化 YMS 年月日 HMS时分秒
    private static final String  nowTimeAll = "yyyy-MM-dd HH:mm:ss";
    private static final String  nowTimeHMS = "HH:mm:ss";
    private static final String  nowTimeYMD = "yyyy-MM-dd";
    private static final String  HNowTimeYMS = "yyyy年MM月dd日";

    /**
     * 现在时间
     * 标准格式 ISO-8601标准格式
     * 22-11-08 14:43:30
     * @return String类型
     */
    public static String now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(nowTimeAll);
        return LocalDateTime.now().format(formatter);
    }

    public String nowTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(nowTimeAll);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 2022-11-08
     *
     * @return String类型
     */
    public static String nowTimeHMS() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(nowTimeHMS);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 14:44:22
     *
     * @return String类型
     */
    public static String nowTimeYMD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(nowTimeYMD);
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 2022年11月08日
     */
    public static String HNowTimeYMD() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(HNowTimeYMS);
        return LocalDateTime.now().format(formatter);
    }
}
