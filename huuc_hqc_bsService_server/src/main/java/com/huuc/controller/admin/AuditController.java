package com.huuc.controller.admin;

import com.huuc.dto.AuditPageDTO;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.PostAuditService;
import com.huuc.vo.AuditPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/audit")
@Api(tags = "审核列表")
public class AuditController {
    @Resource
    private PostAuditService postAuditService;

    @ApiOperation(value = "分页查询审核列表")
    @PostMapping("/page")
    public AjaxResult page(@RequestBody AuditPageDTO params) {
        PageResult<AuditPageVO> pageResult = postAuditService.getPage(params);
        return AjaxResult.success(pageResult);
    }
}
