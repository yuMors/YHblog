package com.sangeng.controller;


import com.sangeng.config.ResponseResult;
import com.sangeng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>分类表 前端控制器</p>
 * 即首页的分页菜单
 * @author Makos
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult<?> getCategoryList(){
        return categoryService.getCategoryList();
    }
}
