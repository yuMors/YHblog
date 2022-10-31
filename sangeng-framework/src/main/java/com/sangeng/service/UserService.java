package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.User;

public interface UserService extends IService<User> {
    /**查询个人信息*/
    ResponseResult<?> userInfo();

    /**更新用户信息*/
    ResponseResult<?> updateUserInfo(User user);

    /**注册用户*/
    ResponseResult<?> register(User user);
}
