package com.huuc.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.huuc.dto.ConfessionStatusDTO;
import com.huuc.pojo.ConfessionLog;
import com.huuc.result.AjaxResult;
import com.huuc.service.ConfessionLogService;
import com.huuc.service.ConfessionStatusUpdateLogService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
@Api(tags = "表白墙管理相关接口")
public class ConfessionWallController {
  private final ConfessionStatusUpdateLogService confessionStatusUpdateLogService;
  private final ConfessionLogService confessionLogService;
   @ApiOperation(value = "更改表白状态")
   @PutMapping("/updateConfessionStatus")
   public AjaxResult updateConfessionStatus(@RequestBody ConfessionStatusDTO confessionStatusDTO) {
       confessionStatusUpdateLogService.updateStatus(confessionStatusDTO);
       return AjaxResult.success("更改成功");
  }

    @ApiOperation(value = "设置取消置顶")
    @PutMapping("/updateTop")
    public AjaxResult updateTop(@RequestBody Map<String, String> requestBody) {
      LambdaUpdateWrapper<ConfessionLog> updateWrapper = new LambdaUpdateWrapper<>();
      updateWrapper.eq(ConfessionLog::getId,requestBody.get("confessionId"))
              .set(ConfessionLog::getIsTop,requestBody.get("newTop"));
        confessionLogService.update(updateWrapper);
        return AjaxResult.success("更改成功");
    }
}
