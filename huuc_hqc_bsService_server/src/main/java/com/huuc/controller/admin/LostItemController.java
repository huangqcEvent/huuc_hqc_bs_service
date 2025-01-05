package com.huuc.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huuc.dto.UpdateStatusDTO;
import com.huuc.pojo.LostItem;
import com.huuc.result.AjaxResult;
import com.huuc.service.LostItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("adminLostItemController")
@RequestMapping("/admin/lostItem")
@AllArgsConstructor
@Slf4j
@Api(tags = " 用户寻物贴相关接口(管理端)")
public class LostItemController {
    private final LostItemService lostItemService;

    @ApiOperation(value = "根据id更新状态")
    @PutMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody UpdateStatusDTO updateStatus) {
        lostItemService.lambdaUpdate()
                .set(LostItem::getStatus, updateStatus.getStatus())
                .eq(LostItem::getId, updateStatus.getId())
                .update();
        return AjaxResult.success();
    }

    @ApiOperation(value = "设置取消置顶")
    @PutMapping("/updateTop")
    public AjaxResult updateTop(@RequestBody Map<String, String> requestBody) {
        LambdaUpdateWrapper<LostItem> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(LostItem::getId,requestBody.get("id"))
                .set(LostItem::getIsTop,requestBody.get("newTop"));
        lostItemService.update(updateWrapper);
        return AjaxResult.success("更改成功");
    }
}
