package com.huuc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@ApiModel("用户身份信息转化记录表")
@TableName("user_identity_update_log")
public class UserIdentityUpdateLog {
    @ApiModelProperty("用户身份转化记录id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("用户原身份")
    @TableField("old_identity")
    private int oldIdentity;

    @ApiModelProperty("用户新身份")
    @TableField("new_identity")
    private int newIdentity;

    @ApiModelProperty("操作管理员Id")
    @TableField("update_admin_id")
    private String updateAdminId;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新原因")
    @TableField("update_reason")
    private String updateReason;
}
