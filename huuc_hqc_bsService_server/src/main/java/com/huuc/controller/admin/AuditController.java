package com.huuc.controller.admin;

import com.huuc.dto.AuditPageDTO;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.PostAuditService;
import com.huuc.service.detail.DetailPageBuilder;
import com.huuc.service.detail.DetailParams;
import com.huuc.service.detail.module.AuditInfoDataModule;
import com.huuc.service.detail.module.PostInfoModule;
import com.huuc.service.detail.module.UserInfoModule;
import com.huuc.vo.AuditPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
    @Resource
    private AuditInfoDataModule auditInfoDataModule;
    @Resource
    private PostInfoModule postInfoModule;
    @Resource
    private UserInfoModule userInfoModule;

    @ApiOperation(value = "分页查询审核列表")
    @PostMapping("/page")
    public AjaxResult page(@RequestBody AuditPageDTO params) {
        PageResult<AuditPageVO> pageResult = postAuditService.getPage(params);
        return AjaxResult.success(pageResult);
    }

    @ApiModelProperty(value = "详情")
    @PostMapping("/getDetail")
    public AjaxResult getDetail(@RequestBody DetailParams params) {
        DetailPageBuilder detailPageBuilder = new DetailPageBuilder(auditInfoDataModule,postInfoModule,userInfoModule);
        return AjaxResult.success(detailPageBuilder.build().getAllData(params));
    }
}
