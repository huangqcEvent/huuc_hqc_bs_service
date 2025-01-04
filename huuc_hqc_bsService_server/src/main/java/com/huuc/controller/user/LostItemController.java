package com.huuc.controller.user;

import com.huuc.context.BaseContext;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.pojo.LostItem;
import com.huuc.result.AjaxResult;
import com.huuc.service.LostItemService;
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

    @PostMapping("/saveLostItem")
    @ApiOperation(value = "保存寻物贴")
    public AjaxResult save(@RequestBody LostItem lostItem){
        lostItem.setUserId(BaseContext.getCurrentId());
        lostItemService.save(lostItem);
        return AjaxResult.success("保存成功");
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