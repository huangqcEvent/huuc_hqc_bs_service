package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("post_interaction")
@ApiModel(value = "PostInteraction对象", description = "分数表（用于协同过滤）")
public class PostInteraction {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty("主键")
    private String id;

    @TableField("user_id")
    @ApiModelProperty("用户ID")
    private String userId;

    @TableField("post_id")
    @ApiModelProperty("帖子ID")
    private String postId;

    @TableField("post_type")
    @ApiModelProperty("帖子类型")
    private Integer postType;

    @TableField("is_liked")
    @ApiModelProperty("是否点赞")
    private Integer isLiked;

    @TableField("is_commented")
    @ApiModelProperty("是否评论")
    private Integer isCommented;

    @TableField("is_favored")
    @ApiModelProperty("是否收藏")
    private Integer isFavored;

    @TableField("total_score")
    @ApiModelProperty("总分")
    private Integer totalScore;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
