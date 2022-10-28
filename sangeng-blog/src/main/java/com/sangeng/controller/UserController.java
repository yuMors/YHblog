package com.sangeng.controller;

import com.sangeng.config.ResponseResult;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询个人信息
     */
    @GetMapping("/userInfo")
    public ResponseResult<?> userInfo() {
        return userService.userInfo();
    }
}
