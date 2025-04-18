package com.huuc.controller.user;


import com.huuc.context.BaseContext;
import com.huuc.dto.UpdateStatusDTO;
import com.huuc.enums.PostTypeEnum;
import com.huuc.pojo.PostInteraction;
import com.huuc.result.AjaxResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.service.ConfessionLogService;
import com.huuc.service.LostItemService;
import com.huuc.service.PostInteractionService;
import com.huuc.service.recommend.InteractionVO;
import com.huuc.service.recommend.RecommendationVO;
import com.huuc.service.recommend.UserCF;
import com.huuc.vo.ClubDetailVO;
import com.huuc.vo.ConfessionDetailVO;
import com.huuc.vo.LostItemDetailVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/postInteraction")
@AllArgsConstructor
@Slf4j
@Api(tags = "点赞收藏评论记录，协同过滤")
public class PostInteractionController {
    private final PostInteractionService postInteractionService;
    private final ConfessionLogService confessionLogService;
    private final LostItemService lostItemService;
    private final ClubRecruitmentNewsService clubRecruitmentNewsService;

    @PostMapping("/likedOrCollect")
    public AjaxResult likedOrCollect(@RequestBody UpdateStatusDTO updateStatusDTO) {
        postInteractionService.updateStatus(updateStatusDTO);
        return AjaxResult.success("操作成功");
    }
    @GetMapping("/interaction")
    public AjaxResult interaction() {
        List<PostInteraction> postInteractions = postInteractionService.list();
        // 构建数据矩阵
        Map<String, List<InteractionVO>> userItemMatrix = buildUserItemMatrix(postInteractions);

        // 目标用户和参数设置
        String targetUserId = BaseContext.getCurrentId();
        int topKUsers = 10;
        int topKPosts = 5; // 假设推荐前5个帖子

        // 基于用户的推荐
        UserCF userCF = new UserCF(userItemMatrix);
        List<RecommendationVO> recommendations = userCF.recommendPosts(targetUserId, topKUsers, topKPosts);

        //查出帖子信息
        recommendations.forEach(recommendation -> {
            switch (Objects.requireNonNull(PostTypeEnum.getByCode(recommendation.getPostType()))){
                case POST_TYPE_CONFESSION:
                    ConfessionDetailVO confessionDetailVO = confessionLogService.getDetailById(recommendation.getPostId());
                    recommendation.setCreateTime(confessionDetailVO.getCreateTime());
                    recommendation.setPostTitle(confessionDetailVO.getTitle());
                    recommendation.setUserId(confessionDetailVO.getUserId());
                    recommendation.setUserName(confessionDetailVO.getUserName());
                    break;
                case POST_TYPE_ITEM:
                    LostItemDetailVO lostItemDetailVO = lostItemService.getDetailById(recommendation.getPostId());
                    recommendation.setCreateTime(lostItemDetailVO.getCreateTime());
                    recommendation.setPostTitle(lostItemDetailVO.getItemName());
                    recommendation.setUserId(lostItemDetailVO.getUserId());
                    recommendation.setUserName(lostItemDetailVO.getUserName());
                    break;
                case POST_TYPE_CLUB:
                    ClubDetailVO detailById = clubRecruitmentNewsService.getDetailById(recommendation.getPostId());
                    recommendation.setCreateTime(detailById.getCreateTime());
                    recommendation.setPostTitle(detailById.getTitle());
                    recommendation.setUserId(detailById.getClubId());
                    recommendation.setUserName(detailById.getClubName());
                    break;
                default:
                    log.error("类名匹配失败{}", recommendation.getPostType());
                    throw new RuntimeException("类名匹配失败" + recommendation.getPostType());
            }
        });

        return AjaxResult.success(recommendations);
    }

    private static Map<String, List<InteractionVO>> buildUserItemMatrix(List<PostInteraction> interactions) {
        return interactions.stream().collect(Collectors.groupingBy(PostInteraction::getUserId,
                Collectors.mapping(interaction -> new InteractionVO(interaction.getPostId(), interaction.getPostType(), interaction.getTotalScore()), Collectors.toList())));
    }
}
