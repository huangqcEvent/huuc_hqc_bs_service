package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("post_audit")
@ApiModel(value = "PostAudit对象", description = "帖子审核表")
@Builder
public class PostAudit {
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "审核流水号")
    private String id;

    @ApiModelProperty(value = "帖子id")
    private String postId;

    @ApiModelProperty(value = "帖子类型 1表白墙 2寻物 3社团")
    private Integer postType;

    @ApiModelProperty(value = "审核状态 1待审核 2通过 3待补正")
    private Integer status;

    @ApiModelProperty(value = "不通过原因")
    private String reason;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "审核人id")
    private String auditUserId;

    @ApiModelProperty(value = "发布类型")
    private Integer releaseType;
}
