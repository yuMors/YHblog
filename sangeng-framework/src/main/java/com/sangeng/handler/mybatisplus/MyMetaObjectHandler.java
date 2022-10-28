package com.sangeng.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sangeng.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.now();
    String myLocalDateTimeNow = localDateTime.format(formatter);
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /*
            在创建用户时 是不存在的 不加这个判断就会漏掉这种情况
         */
        Long userId = null;
        userId = SecurityUtils.getUserId();
        /*try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//表示是自己创建的
        }*/
        this.setFieldValByName("createTime",myLocalDateTimeNow,metaObject);
        this.setFieldValByName("updateTime",myLocalDateTimeNow,metaObject);
        this.setFieldValByName("createBy",userId,metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", myLocalDateTimeNow, metaObject);
        this.setFieldValByName("", SecurityUtils.getUserId(), metaObject);
    }
}
