package com.sangeng.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

@Component
public class TestJob {
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*LocalDateTime localDateTime = LocalDateTime.now();
    String myLocalDateTimeNow = localDateTime.format(formatter);*/

    //String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    /**
     * 1、@Scheduled 标识这是一个任务型的方法
     * cron = "0/5 * * * * ? *"
     * 从每一分钟的零秒开始 每5秒执行一下代码
     * cron = "0 0/1 * * * ?"
     * 表示每1分钟 执行任务
     */

    @Scheduled(cron = "* 0/10 * * * ?")
    public void testJob(){
        //要执行的diamagnetic
        System.out.println("testJob定时任务执行了: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
