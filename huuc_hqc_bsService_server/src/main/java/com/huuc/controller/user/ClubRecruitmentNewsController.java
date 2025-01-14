package com.huuc.controller.user;

import com.aliyun.oss.common.parser.ResponseParseException;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.AjaxResult;
import com.huuc.service.ClubRecruitmentNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/club")
@Api(tags = "社团纳新/公告管理")
public class ClubRecruitmentNewsController {
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;

    @PostMapping("/save")
    @ApiModelProperty("添加社团纳新/公告(待审核状态)")
    public AjaxResult save(@RequestBody ClubRecruitmentNews clubRecruitmentNews) throws ResponseParseException {
        clubRecruitmentNewsService.saveClub(clubRecruitmentNews);
        return AjaxResult.success("已提交，审核通过后将发布");
    }
}
