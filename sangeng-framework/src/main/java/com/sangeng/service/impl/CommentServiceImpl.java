package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.config.ResponseResult;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.entity.Comment;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.CommentVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;


    /**
     * 评论列表 根评论
     *  @param articleId 文章ID
     * @param pageNum   当前页
     * @param pageSize  每页数量
     * @param commentType
     */
    @Override
    public ResponseResult<?> commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(SysConstants.ARTICLE_COMMENT.equals(commentType),
                        Comment::getArticleId, articleId);
        //根评论rootId为-1
        queryWrapper.eq(Comment::getRootId, -1);
        //评论类型
        queryWrapper.eq(Comment::getType,commentType);
        //根据创建时间降序排列
        queryWrapper.orderByDesc(Comment::getCreateTime);
        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<Comment> records = page.getRecords();

        List<CommentVo> commentVoList = toCommentVoList(records);

        //List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合 并且赋值给相应的属性
        //commentVoList.forEach(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())));
        //查询对应的子评论
        //赋值
        /*for (CommentVo commentVo : commentVoList) {
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }*/
        //查询对应的子评论
        commentVoList.forEach(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())));

        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }

    /**
     * 根据根评论的id查询对应的子评论的集合
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        //降序
        //queryWrapper.orderByDesc(Comment::getCreateTime);
        //升序
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    /**
     * 通过createBy查询用户的昵称并赋值
     * 并封装返回
     *
     * @param list
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        /*if (Objects.isNull(commentVoList)) {
            System.err.println("to commentVoList is null");
            return null;
        }*/
        //遍历Vo集合
        for (CommentVo commentVo : commentVoList) {
            //通过createBy查询用户的昵称并赋值
            /*在新增评论的时候那个createBy直接赋值为-1 然后他根据-1查自然查不到id为-1的 */
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询

            if (commentVo.getToCommentId() != -1) {
                //Long userId = commentVo.getToCommentId();
                User user = userService.getById(commentVo.getToCommentUserId());
                //必须进行判空操作 否则如果没有就会空指针异常
                if (!Objects.isNull(user)) {
                    commentVo.setToCommentUserName(user.getNickName());
                } else {
                    commentVo.setToCommentUserName("This ToCommentUserName is null");
                }
                //String toCommentName = userService.getById(commentVo.getToCommentId()).getNickName();
            }
        }
        return commentVoList;
    }


    /**
     * 回复评论
     * 在新增评论的时候那个createBy直接赋值为-1
     * 然后他根据-1查自然查不到id为-1的
     */
    @Override
    public ResponseResult<?> addComment(Comment comment) {
        //评论内容不能为空
        //comment.setCreateBy(1L);
        //从前端只传过来了部分字段 剩余字段需要赋值 不建议用默认的
        //comment.setCreateBy(SecurityUtils.getUserId());
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        /*在这里处理一下异常 判断是否登录
          已登录的话可以操作 不登陆就会出现异常 然后就捕获异常 提示登录*/
        try {
            save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        return ResponseResult.okResult();
    }
}
