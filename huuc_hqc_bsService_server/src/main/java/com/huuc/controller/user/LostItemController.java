package com.huuc.controller.user;

import com.huuc.context.BaseContext;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.enums.PostStatusEnum;
import com.huuc.enums.PostTypeEnum;
import com.huuc.pojo.LostItem;
import com.huuc.result.AjaxResult;
import com.huuc.service.LostItemService;
import com.huuc.service.PostAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("userLostItemController")
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
@Api(tags = " 用户寻物贴相关接口")
public class LostItemController {
    private final LostItemService lostItemService;
    private final PostAuditService postAuditService;

    @PostMapping("/saveLostItem")
    @ApiOperation(value = "保存寻物贴")
    public AjaxResult save(@RequestBody LostItem lostItem){
        Integer releaseType = lostItem.getStatus();
        lostItem.setUserId(BaseContext.getCurrentId());
        lostItem.setStatus(PostStatusEnum.POST_STATUS_AUDIT.getCode());
        lostItemService.save(lostItem);
        postAuditService.saveAutoAudit(lostItem.getId(), PostTypeEnum.POST_TYPE_ITEM.getCode(),releaseType);
        return AjaxResult.success("提交成功，审核通过后将发布");
    }

    @PostMapping("/lostItem/page")
    @ApiOperation(value = "分页查询寻物贴")
    public AjaxResult page(@RequestBody LostItemPageDTO lostItemPageDTO){
        return AjaxResult.success(lostItemService.lostPage(lostItemPageDTO));
    }

    @GetMapping("/lostItem/detail/{id}")
    @ApiOperation(value = "寻物贴详情")
    public AjaxResult detail(@PathVariable("id") String id){
        return AjaxResult.success(lostItemService.getDetailById(id));
    }
}