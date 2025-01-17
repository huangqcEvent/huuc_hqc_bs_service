package com.huuc.service.detail.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "寻物贴详情返回", description = "寻物贴详情返回")
@Data
public class LostItemInfoData {
    @ApiModelProperty("寻物贴id")
    private String id;

    @ApiModelProperty("失物名称")
    private String itemName;

    @ApiModelProperty("类型 1寻物 2寻主")
    private String type;

    @ApiModelProperty("失物描述")
    private String itemDesc;

    @ApiModelProperty("丢失地点")
    private String location;

    @ApiModelProperty("联系方式（电话）")
    private String phone;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("图片1")
    private String image1;

    @ApiModelProperty("图片2")
    private String image2;

    @ApiModelProperty("图片3")
    private String image3;
}
