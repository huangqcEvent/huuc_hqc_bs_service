package com.huuc.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huuc.context.BaseContext;
import com.huuc.pojo.ClubResume;
import com.huuc.result.AjaxResult;
import com.huuc.service.ClubResumeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user/news")
@Api(tags = "社团纳新投递")
@Slf4j
public class ClubNewsResumeController {
    @Resource
    private ClubResumeService clubResumeService;

    @PostMapping("/save")
    public AjaxResult save(@RequestBody ClubResume clubResume) {
        //一个用户只能投递一个职位一次
        LambdaQueryWrapper<ClubResume> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ClubResume::getUserId,BaseContext.getCurrentId());
        queryWrapper.eq(ClubResume::getClubNewsId,clubResume.getClubNewsId());
        ClubResume clubResume1 = clubResumeService.getOne(queryWrapper);
        if (clubResume1 != null){
            return AjaxResult.error("您已经投递过该职位");
        }
        clubResume.setUserId(BaseContext.getCurrentId());
        clubResume.setCreateTime(LocalDateTime.now());
        clubResumeService.save(clubResume);
        return AjaxResult.success("投递成功");
    }
}
