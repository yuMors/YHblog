package com.sangeng.service;

import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.User;

public interface BlogLoginService {
    ResponseResult<?> login(User user);

    ResponseResult<?> logout();
}
