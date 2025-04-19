package com.huuc.controller.user;

import com.huuc.enums.PostTypeEnum;
import com.huuc.event.AutoAuditEvent;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.pojo.PostAudit;
import com.huuc.result.AjaxResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.service.PostAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
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
    @Resource
    private ApplicationContext applicationContext;

    @PostMapping("/save")
    @ApiModelProperty("添加社团纳新/公告(待审核状态)")
    public AjaxResult save(@RequestBody ClubRecruitmentNews clubRecruitmentNews){
        //首先判断是否是打回重新走流程的
        if(clubRecruitmentNews.getId() != null) {
            //查出审核流水号，直接发布事件即可
            PostAudit postAudit = postAuditService.getOne(clubRecruitmentNews.getId(), PostTypeEnum.POST_TYPE_CLUB.getCode());
            //直接发布敏感词审核事件
            applicationContext.publishEvent(new AutoAuditEvent(postAudit.getId()));
            return AjaxResult.success("已重新提交审核");
        }else {
            Integer releaseType = clubRecruitmentNews.getStatus();
            //step1 插入社团纳新/公告表
            clubRecruitmentNewsService.saveClub(clubRecruitmentNews);
            //step2 插入帖子审核表
            postAuditService.saveAutoAudit(clubRecruitmentNews.getId(), PostTypeEnum.POST_TYPE_CLUB.getCode(), releaseType);
            return AjaxResult.success("提交成功，审核通过后将发布");
        }
    }

    @GetMapping("/getDetail/{id}")
    @ApiModelProperty("社团纳新/公告详情")
    public AjaxResult getDetailById(@PathVariable("id") String id){
        return AjaxResult.success(clubRecruitmentNewsService.getDetailById(id));
    }
}
