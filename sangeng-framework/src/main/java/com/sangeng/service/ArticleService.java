package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.entity.Article;
import com.sangeng.config.ResponseResult;
import com.sangeng.config.SYResponse;

public interface ArticleService extends IService<Article> {

    /**
     * 查询热门文章 封装成ResponseResult返回
     * @return
     */
    ResponseResult hotArticleList();

    SYResponse<?> hotList();

    ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult<?> getArticleDetail(Long id);
}
