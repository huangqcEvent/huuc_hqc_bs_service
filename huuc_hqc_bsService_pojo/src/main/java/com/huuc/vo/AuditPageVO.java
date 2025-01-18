package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.AuditStatusEnum;
import com.huuc.enums.PostTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "社团审核返回")
public class AuditPageVO {
    private String id;//流水号
    private String postId;//贴id
    @GetMsg(enumClass = PostTypeEnum.class)
    private Integer postType;//贴大类型
    private Integer postSubType;//贴小类型
    private String userId;//用户id
    private String userName;//用户名称
    private String title;//标题
    @GetMsg(enumClass = AuditStatusEnum.class)
    private Integer status;//状态
    private String createTime;//提交时间
    private String autoAuditTime;//机器审核时间
    private String auditTime;//审核时间
    private String auditUserName;//审核人名称
    private String reason;//拒绝原因
}
