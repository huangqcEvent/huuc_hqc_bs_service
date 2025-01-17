package com.huuc.service.detail;

import lombok.Data;

@Data
public class DetailParams {
    private String postId;//帖子id
    private Integer postType;//帖子类型
    private String userId;//用户id
}
