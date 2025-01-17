package com.huuc.service.detail.module;

import com.huuc.enums.PostTypeEnum;
import com.huuc.exception.ResponseException;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.service.ConfessionLogService;
import com.huuc.service.LostItemService;
import com.huuc.service.detail.DetailParams;
import com.huuc.service.detail.GetDetailModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PostInfoModule implements GetDetailModule<Object> {
    @Resource
    private ConfessionLogService confessionLogService;
    @Resource
    private LostItemService lostItemService;
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;
    @Override
    public Object getData(DetailParams params) {
         PostTypeEnum postTypeEnum = PostTypeEnum.getByCode(params.getPostType());
        if (postTypeEnum != null) {
            switch (postTypeEnum){
                case POST_TYPE_CONFESSION:
                    return confessionLogService.getDetailById1(params.getPostId());
                case POST_TYPE_ITEM:
                    return lostItemService.getDetailById1(params.getPostId());
                case POST_TYPE_CLUB:
                    return clubRecruitmentNewsService.getDetailById1(params.getPostId());
                default:
                    throw new ResponseException("类名匹配失败");
            }
        }
        throw new ResponseException("贴详情错误");
    }
}
