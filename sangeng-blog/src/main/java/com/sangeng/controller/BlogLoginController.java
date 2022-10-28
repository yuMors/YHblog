package com.sangeng.controller;

import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.User;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    /**
     * 即使前端已经有验证了 后端仍需要验证
     * 一些懂帝会根据一些请求地址和请求参数等信息直接伪造HTTP请求发起访问
     * 如果后端没有验证 就有可能越过前端验证直接访问数据
     * 前端防君子 后端防小人
     * @param user json 用户信息
     * @return code msg
     */
    @PostMapping("/login")
    public ResponseResult<?> login(@RequestBody User user){
        //判断用户名
        if (!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult<?> logout(){
        //删除redis中的用户信息
        return blogLoginService.logout();
    }
}
