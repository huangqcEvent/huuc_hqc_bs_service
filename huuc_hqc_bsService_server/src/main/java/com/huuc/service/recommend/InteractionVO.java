package com.huuc.service.recommend;

import lombok.Data;

@Data
public class InteractionVO {
    private String postId;
    private Integer postType;
    private Integer totalScore;

    public InteractionVO(String postId, Integer postType, Integer totalScore) {
        this.postId = postId;
        this.postType = postType;
        this.totalScore = totalScore;
    }
}
