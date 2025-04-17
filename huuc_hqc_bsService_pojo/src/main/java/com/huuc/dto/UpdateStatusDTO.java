package com.huuc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "根据id更改状态入参", description = "根据id更改状态入参")
public class UpdateStatusDTO {
    @ApiModelProperty("帖子id")
    private String id;

    @ApiModelProperty("帖子类型")
    private Integer postType;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("更改类型（1点赞 2收藏 3评论）")
    private Integer updateType;

    public UpdateStatusDTO(String id,Integer postType,Integer status,Integer updateType){
        this.id = id;
        this.postType = postType;
        this.status = status;
        this.updateType = updateType;
    }
    UpdateStatusDTO(){}
}
