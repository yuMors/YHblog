package com.sangeng.controller;

import com.sangeng.config.ResponseResult;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.entity.Comment;
import com.sangeng.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论表 前端控制器
 *
 * @author Makos
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 显示评论列表
     * @param articleId 文章ID
     * @param pageNum 当前页
     * @param pageSize  每页数量
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult<?> commentList(Long articleId,
                                         Integer pageNum,
                                         Integer pageSize){
        return commentService.commentList(SysConstants.ARTICLE_COMMENT,
                            articleId,pageNum, pageSize);
    }

    /**
     * 回复评论
     */
    @PostMapping
    public ResponseResult<?> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    /**
     * 友链 查询
     * ctrl shift u 大小写切换
     */
    @GetMapping("/linkCommentList")
    public ResponseResult<?> linkCommentList(Integer pageNum,
                                             Integer pageSize){
        return commentService.commentList(SysConstants.LINK_COMMENT,
                                null,pageNum, pageSize);
    }

}
