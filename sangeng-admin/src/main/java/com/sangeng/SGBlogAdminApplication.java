package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sangeng.mapper")
public class SGBlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SGBlogAdminApplication.class,args);
        System.out.println("接口Swagger文档: "+"http://localhost:8989/swagger-ui.html");
    }
}
