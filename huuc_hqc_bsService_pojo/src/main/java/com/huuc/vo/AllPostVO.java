package com.huuc.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "词云数据返回")
public class AllPostVO {
    private String name;
    private Integer value;
    private Integer postType;
    private String postId;
}
