package com.sangeng.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    /**
     * beanCopy 实现字段的变化
     * example: User user = loginUser.getUser();
     * example: UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
     * @param source 源目标
     * @param clazz  要转换的目标的类对象
     * @param <V>    类型为V 传入什么类型 返回什么类型
     * @return 传入什么类型 返回什么类型
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    /**
     * copyBean返回的是一个对象 这里把对象放进集合返回
     * @param list 源对象集合 list类型
     * @param clazz 目标Vo对象 class类型
     * @param <O> 源对象
     * @param <V> 目标对象
     * @return List<V> 返回list类型的目标对象
     */
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        //可以用传统的for循环
        //这里用stream流
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }

    /*public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("ss");
        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.err.println(hotArticleVo);
    }*/
}
