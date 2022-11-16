package com.sangeng.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 文件名字生成工具类
 * 生成照片的名字 按照一定的规则如果没有指定的话
 */
public class PathUtils {

    public static String generateFilePath(String fileName) {
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String myLocalDateTimeNow = localDateTime.format(formatter);*/

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

        String dataName = LocalDateTime.now().format(dateTimeFormatter);
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = simpleDateFormat.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //String uuidNew = dataName+uuid.substring(8);
        String uuidNews = dataName + "YH" + "-" + uuid.substring(18);
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return datePath + uuidNews + fileType;
        //return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
    }
}

