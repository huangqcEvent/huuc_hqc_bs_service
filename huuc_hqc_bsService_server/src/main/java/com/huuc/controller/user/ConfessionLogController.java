package com.huuc.controller.user;

import com.huuc.context.BaseContext;
import com.huuc.dto.ConfessionLogDTO;
import com.huuc.enums.PostStatusEnum;
import com.huuc.enums.PostTypeEnum;
import com.huuc.pojo.ConfessionLog;
import com.huuc.result.AjaxResult;
import com.huuc.service.ConfessionLogService;
import com.huuc.service.PostAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
@Api(tags = " 用户表白墙相关接口")
public class ConfessionLogController {

    private final ConfessionLogService confessionLogService;
    private final PostAuditService postAuditService;


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

    @PostMapping("/saveConfession")
    @ApiOperation(value = "表白墙信息保存")
    public AjaxResult saveConfession(@RequestBody ConfessionLog confessionLog) {
        Integer releaseType = confessionLog.getStatus();
        confessionLog.setStatus(PostStatusEnum.POST_STATUS_AUDIT.getCode());
        confessionLog.setUserId(BaseContext.getCurrentId());
        confessionLogService.save(confessionLog);
        postAuditService.saveAutoAudit(confessionLog.getId(), PostTypeEnum.POST_TYPE_CONFESSION.getCode(),releaseType);
        return AjaxResult.success("提交成功，审核通过后将发布");
    }
}
