package com.huuc.service.recommend;

import com.huuc.annotation.GetMsg;
import com.huuc.enums.PostTypeEnum;
import lombok.Data;

@Data
public class RecommendationVO {
    private String postId;
    @GetMsg(enumClass = PostTypeEnum.class)
    private Integer postType;
    private Double score;
    private String userId;
    private String userName;
    private String postTitle;
    private String createTime;


    public RecommendationVO(String postId, Integer postType, double score) {
        this.postId = postId;
        this.postType = postType;
        this.score = score;
    }
}
