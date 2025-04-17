package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comment")
@ApiModel(value = "评论表")
public class Comment {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("帖子id")
    private String postId;

    @ApiModelProperty("帖子类型 1表白 2寻物 3社团")
    private Integer postType;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论状态 1正常 2删除")
    private Integer status;

    @ApiModelProperty("是否置顶 0否 1是")
    private Integer isTop;
}
