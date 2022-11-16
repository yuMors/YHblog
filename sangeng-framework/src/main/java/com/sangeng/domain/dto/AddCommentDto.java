package com.sangeng.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据传输对象（DTO）(Data Transfer Object)
 * DTO代表服务层需要接收的数据和返回的数据
 * DTO是传输模型，数据传输对象，在这里泛指用于展示层与服务层之间的数据传输对象
 * VO代表展示层需要显示的数据
 * VO(View Object)：VO是显示视图模型，视图对象，用于展示层，
 * 它的作用是把某个指定页面（或组件）的所有数据封装起来
 * <p>
 * 评论表Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加评论Dto")
public class AddCommentDto {

    private Long id;

    /**
     * 评论类型（0代表文章评论，1代表友链评论）
     */
    @ApiModelProperty(notes = "评论类型(0代表文章评论，1代表友链评论）")
    private String type;

    /**
     * 文章id
     */
    @ApiModelProperty(notes = "文章id")
    private Long articleId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    /**
     * 评论创建人id编号
     */
    private Long createBy;

    private String createTime;

    private Long updateBy;

    private String updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;


}


