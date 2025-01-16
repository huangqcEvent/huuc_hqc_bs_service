package com.huuc.controller.user;

import com.huuc.enums.PostTypeEnum;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.AjaxResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.service.PostAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("userClubRecruitmentNewsController")
@RequestMapping("/user/club")
@Api(tags = "社团纳新/公告管理")
@Slf4j
public class ClubRecruitmentNewsController {
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;
    @Resource
    private PostAuditService postAuditService;

    @PostMapping("/save")
    @ApiModelProperty("添加社团纳新/公告(待审核状态)")
    public AjaxResult save(@RequestBody ClubRecruitmentNews clubRecruitmentNews){
        Integer releaseType = clubRecruitmentNews.getStatus();
        //step1 插入社团纳新/公告表
            clubRecruitmentNewsService.saveClub(clubRecruitmentNews);
        //step2 插入帖子审核表
            postAuditService.saveAutoAudit(clubRecruitmentNews.getId(), PostTypeEnum.POST_TYPE_CLUB.getCode(),releaseType);
        return AjaxResult.success("提交成功，审核通过后将发布");
    }
}
