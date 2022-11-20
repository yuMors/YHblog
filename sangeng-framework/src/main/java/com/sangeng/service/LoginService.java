package com.sangeng.service;

import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.User;

public interface LoginService {

    ResponseResult<?> login(User user);
}
