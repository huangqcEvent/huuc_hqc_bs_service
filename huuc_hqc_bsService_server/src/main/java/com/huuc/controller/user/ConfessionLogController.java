package com.huuc.controller.user;

import com.huuc.result.AjaxResult;
import com.huuc.service.ConfessionLogService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/confessionLog")
@AllArgsConstructor
@Slf4j
@ApiOperation(value = " 表白墙管理相关接口")
public class ConfessionLogController {

    private final ConfessionLogService confessionLogService;

    @GetMapping
    public AjaxResult getAllConfessionLogs() {
        return AjaxResult.success(confessionLogService.list());
    }
}
