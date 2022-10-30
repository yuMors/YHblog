package com;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class ShiYunGenerator {
    // 配置输出位置 最后一个/是用来连接下面的包
    //C:\Users\30676\Desktop\day-java\ideaWork\SGBlog\sangeng-framework\src\main\java\com
    //final String outputFileSy = "C://Users/30676/Desktop/day5/day5Boot/src/main/java/com/day5Boot/";
    final String outputFileSy = "C://Users/30676/Desktop/day-java/ideaWork/SGBlog/sangeng-framework/src/main/java/com/sangeng/";
    // 设置需要生成的表名
    final String databaseTable = "sg_comment";
    // 设置过滤表前缀
    final String tablePrefix = "sg_";

    String name = "Makos";
    String name2 = "YEHANG";
    @Test
    public void generator() {
        // 配置数据库
        String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/sg_blog";

        FastAutoGenerator.create(mysqlUrl, "root", "root")
                .globalConfig(builder -> {
                    builder.author("YEHANG") // 设置作者
                            //.enableSwagger() // 开启swagger模式 一些文档注解
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()//不打开输出目录
                            .outputDir(outputFileSy); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("generator")//设置输出包名
                            .entity("domain");// 设置实体类包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(databaseTable)// 设置需要生成的表名
                            .addTablePrefix(tablePrefix)// 设置过滤表前缀
                            .entityBuilder()//开启实体类控制
                            .enableLombok()//启用Lombok注解开发
                            .controllerBuilder()
                            .enableRestStyle();//开启Rest风格的controller

                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
