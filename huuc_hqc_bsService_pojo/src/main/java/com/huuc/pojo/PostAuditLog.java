package com.huuc.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(description = "帖子审核记录表")
@TableName("post_audit_log")
@Data
@Builder
public class PostAuditLog {
    @ApiModelProperty(value = "审核记录id", required = true)
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "审核流水号", required = true)
    private String auditId;

    @ApiModelProperty(value = "审核类型 1机器敏感词 2人工", required = true)
    private Integer auditType;

    @ApiModelProperty(value = "审核时间", required = true)
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "审核人名称", required = true)
    private String auditUser;

    @ApiModelProperty(value = "审核结果 1通过 2不通过", required = true)
    private Integer auditResult;

    @ApiModelProperty(value = "审核备注")
    private String auditRemark;

    @ApiModelProperty(value = "是否最新记录1是0不是")
    private Integer isLatest;
}
