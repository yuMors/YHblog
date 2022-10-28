package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.Category;

public interface CategoryService extends IService<Category> {
    ResponseResult<?> getCategoryList();
}
