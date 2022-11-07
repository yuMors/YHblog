package com.sangeng.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {
    /**
     * 这个run方法是在程序执行后才会执行的方法
     * 必须由spring容器管理
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("run方法的初始化");
    }
}
