package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.config.ResponseResult;
import com.sangeng.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    /**
     * 评论列表
     * @param articleId 文章ID
     * @param pageNum 当前页
     * @param pageSize 每页数量
     * @param commentType
     */
    ResponseResult<?> commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 回复评论
     */
    ResponseResult<?> addComment(Comment comment);
}
