package com.huuc.controller.user;

import com.huuc.dto.ConfessionLogDTO;
import com.huuc.result.AjaxResult;
import com.huuc.service.ConfessionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
@Api(value = " 用户表白墙相关接口")
public class ConfessionLogController {

    private final ConfessionLogService confessionLogService;

    @GetMapping("/confessionLog")
    @ApiOperation(value = "表白信息分页查询")
    public AjaxResult getAllConfessionLogs(ConfessionLogDTO confessionLogDTO) {
        return AjaxResult.success(confessionLogService.confessionPageQuery(confessionLogDTO));
    }

    @GetMapping("/getDetail/{id}")
    @ApiOperation(value = "表白信息详情根据id查询")
    public AjaxResult getDetailById(@PathVariable("id") String id) {
        return AjaxResult.success(confessionLogService.getDetailById(id));
    }
}
