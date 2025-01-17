package com.huuc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "社团管理分页返回")
public class ClubPageVO {
    @ApiModelProperty(value = "社团纳新/公告id")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "社团id")
    private String clubId;

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型 1纳新 2公告", required = true)
    private Integer type;

    @ApiModelProperty(value = "状态 1公开中 2私密 3删除 4待审核 5已招满", required = true)
    private Integer status;

    @ApiModelProperty(value = "计划纳新人数")
    private Integer number;

    @ApiModelProperty(value = "已招人数")
    private Integer already;

    @ApiModelProperty(value = "时间类型 1长期 2限时")
    private Integer timeType;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
