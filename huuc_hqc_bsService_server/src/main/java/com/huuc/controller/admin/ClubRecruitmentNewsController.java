package com.huuc.controller.admin;

import com.huuc.dto.ClubPageDTO;
import com.huuc.dto.UpdateStatusDTO;
import com.huuc.pojo.ClubRecruitmentNews;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.ClubRecruitmentNewsService;
import com.huuc.vo.ClubPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController("adminClubRecruitmentNewsController")
@RequestMapping("/admin/club")
@Api(tags = "社团纳新/公告管理")
public class ClubRecruitmentNewsController {
    @Resource
    private ClubRecruitmentNewsService clubRecruitmentNewsService;

    @PostMapping("/page")
    public AjaxResult getPage(@RequestBody ClubPageDTO clubPageDTO) {
        PageResult<ClubPageVO> pageResult = clubRecruitmentNewsService.getPage(clubPageDTO);
        return AjaxResult.success(pageResult);
    }

    @ApiOperation(value = "根据id更新状态")
    @PutMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody UpdateStatusDTO updateStatus) {
        clubRecruitmentNewsService.lambdaUpdate()
                .set(ClubRecruitmentNews::getStatus, updateStatus.getStatus())
                .eq(ClubRecruitmentNews::getId, updateStatus.getId())
                .update();
        return AjaxResult.success("更改状态成功");
    }

    @ApiOperation(value = "排序修改")
    @PutMapping("/updateSort")
    public AjaxResult updateSort(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");
        String sort = requestBody.get("sort");
        clubRecruitmentNewsService.lambdaUpdate()
                .set(ClubRecruitmentNews::getSort, Integer.parseInt(sort))
                .eq(ClubRecruitmentNews::getId, id)
                .update();
        return AjaxResult.success("排序修改成功");
    }
}
