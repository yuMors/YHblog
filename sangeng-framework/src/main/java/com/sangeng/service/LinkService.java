package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.Link;

public interface LinkService extends IService<Link> {

    ResponseResult<?> getAllLink();
}
