package com.sangeng.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author YEHANG
 * @since 2022-11-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sg_tag")
@ApiModel(value = "Tag对象", description = "标签")
public class Tag {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标签名")
    private String name;

    private Long createBy;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    private Long updateBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    @ApiModelProperty("备注")
    private String remark;


}
