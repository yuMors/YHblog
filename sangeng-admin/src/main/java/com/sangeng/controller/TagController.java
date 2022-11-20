package com.sangeng.controller;


import com.sangeng.config.ResponseResult;
import com.sangeng.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-14
 */
@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签")
public class TagController {

    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "标签列表",notes = "获取所有的标签")
    @GetMapping("/list")
    public ResponseResult<?> list(){
        return ResponseResult.okResult(tagService.list());
    }
}
