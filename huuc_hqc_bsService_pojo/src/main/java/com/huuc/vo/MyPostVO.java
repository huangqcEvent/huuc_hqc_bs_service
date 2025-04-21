package com.huuc.vo;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.PostStatusEnum;
import com.huuc.enums.PostTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "我的帖子列表返回")
public class MyPostVO {
    private String postId;//贴id
    @GetMsg(enumClass = PostTypeEnum.class)
    private Integer postType;//贴大类型
    private Integer postSubType;//贴小类型
    private String title;//标题
    @GetMsg(enumClass = PostStatusEnum.class)
    private Integer status;//状态
    private String createTime;//提交时间
    private String content;
    private String ImageUrl;
}
