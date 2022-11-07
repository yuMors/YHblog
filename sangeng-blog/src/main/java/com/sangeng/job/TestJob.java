package com.sangeng.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestJob {

    /**
     * 1、@Scheduled 标识这是一个任务型的方法
     * cron = "0/5 * * * * ? *"
     * 从每一分钟的零秒开始 每5秒执行一下代码
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void testJob(){
        //要执行的diamagnetic
        System.out.println("定时任务执行了 + "+ LocalDateTime.now());
    }
}
