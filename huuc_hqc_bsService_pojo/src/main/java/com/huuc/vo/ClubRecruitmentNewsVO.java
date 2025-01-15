package com.huuc.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "社团审核返回")
public class ClubRecruitmentNewsVO {
    private String id;//流水号
    private String postId;//贴id
    private String postType;//贴大类型
    private String postSubType;//贴小类型
    private String userId;//用户id
    private String userName;//用户名称
    private String title;//标题
    private Integer status;//状态
    private String createTime;//提交时间
    private String auditTime;//审核时间
    private String auditUserId;//审核人
    private String auditUserName;//审核人名称
    private String reason;//拒绝原因
}
