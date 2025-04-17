package com.huuc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.context.BaseContext;
import com.huuc.dto.UpdateStatusDTO;
import com.huuc.enums.UpdateTypeEnum;
import com.huuc.mapper.PostInteractionMapper;
import com.huuc.pojo.PostInteraction;
import com.huuc.service.PostInteractionService;
import org.springframework.stereotype.Service;

@Service
public class PostInteractionServiceImpl extends ServiceImpl<PostInteractionMapper, PostInteraction> implements PostInteractionService {
    @Override
    public void updateStatus(UpdateStatusDTO updateStatusDTO) {
        //先判断有没有记录
        PostInteraction postInteraction = this.getOne(new LambdaUpdateWrapper<PostInteraction>()
                .eq(PostInteraction::getPostId, updateStatusDTO.getId())
                .eq(PostInteraction::getPostType, updateStatusDTO.getPostType())
                .eq(PostInteraction::getUserId, BaseContext.getCurrentId()));
        if (postInteraction == null) {
            postInteraction = new PostInteraction();
            postInteraction.setUserId(BaseContext.getCurrentId());
            postInteraction.setPostId(updateStatusDTO.getId());
            postInteraction.setPostType(updateStatusDTO.getPostType());
            if(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_LIKED.getCode())) {
                postInteraction.setIsLiked(updateStatusDTO.getStatus());
                postInteraction.setTotalScore(2);
            }else if(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_FAVORED.getCode())) {
                postInteraction.setIsFavored(updateStatusDTO.getStatus());
                postInteraction.setTotalScore(5);
            }else{
                postInteraction.setIsCommented(updateStatusDTO.getStatus());
                postInteraction.setTotalScore(3);
            }
            this.save(postInteraction);
        }else {
        LambdaUpdateWrapper<PostInteraction> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PostInteraction::getPostId, updateStatusDTO.getId())
                .eq(PostInteraction::getPostType, updateStatusDTO.getPostType())
                .eq(PostInteraction::getUserId, BaseContext.getCurrentId())
                .set(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_LIKED.getCode()), PostInteraction::getIsLiked, updateStatusDTO.getStatus())
                //分数加三
                .setSql(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_LIKED.getCode()),updateStatusDTO.getStatus()==1?"total_score = total_score + 2":"total_score = total_score - 2")
                .set(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_FAVORED.getCode()), PostInteraction::getIsFavored, updateStatusDTO.getStatus())
                .setSql(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_FAVORED.getCode()),updateStatusDTO.getStatus()==1?"total_score = total_score + 5":"total_score = total_score - 5")
                .set(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_COMMENTED.getCode()), PostInteraction::getIsCommented, updateStatusDTO.getStatus())
                .setSql(updateStatusDTO.getUpdateType().equals(UpdateTypeEnum.IS_COMMENTED.getCode()), updateStatusDTO.getStatus()==1?"total_score = total_score + 3":"total_score = total_score - 3");
        this.update(updateWrapper);
    }
        }
}
