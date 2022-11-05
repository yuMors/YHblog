package com.sangeng.controller;

import com.sangeng.annotation.SystemLog;
import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.User;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 更新用户信息
     */
    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<?> updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    /**
     * 注册用户
     * @param user 用户注册填写的信息 用User类接收
     */
    @PostMapping("/register")
    public ResponseResult<?> register(@RequestBody User user){
        return userService.register(user);
    }
}
