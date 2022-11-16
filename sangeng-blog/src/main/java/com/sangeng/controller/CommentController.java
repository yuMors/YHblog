package com.sangeng.controller;

import com.sangeng.config.ResponseResult;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.dto.AddCommentDto;
import com.sangeng.domain.entity.Comment;
import com.sangeng.service.CommentService;
import com.sangeng.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "评论")
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
    @ApiOperation(value = "回复评论",notes = "回复评论")
    public ResponseResult<Comment> addComment(@RequestBody AddCommentDto addCommentDto){
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    /**
     * 友链 查询
     * ctrl shift u 大小写切换
     */
    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取所有的友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页的数据个数",required = true)
    })
    public ResponseResult<?> linkCommentList(Integer pageNum,
                                             Integer pageSize){
        return commentService.commentList(SysConstants.LINK_COMMENT,
                                null,pageNum, pageSize);
    }

}
