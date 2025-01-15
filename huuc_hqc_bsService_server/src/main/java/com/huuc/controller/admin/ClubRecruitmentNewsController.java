package com.huuc.controller.admin;

import com.huuc.dto.ClubRecruitmentNewsDTO;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.vo.ClubRecruitmentNewsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("adminClubRecruitmentNewsController")
@RequestMapping("/admin/club")
@Api(tags = "社团纳新/公告管理")
public class ClubRecruitmentNewsController {
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;

    @ApiOperation(value = "分页查询社团贴列表")
    @PostMapping("/page")
    public AjaxResult page(@RequestBody ClubRecruitmentNewsDTO params) {
        PageResult<ClubRecruitmentNewsVO> pageResult = clubRecruitmentNewsService.getPage(params);
        return AjaxResult.success(pageResult);
    }
}
