package com.huuc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huuc.context.BaseContext;
import com.huuc.dto.ConfessionStatusDTO;
import com.huuc.mapper.ConfessionStatusUpdateLogMapper;
import com.huuc.pojo.ConfessionLog;
import com.huuc.pojo.ConfessionStatusUpdateLog;
import com.huuc.service.ConfessionLogService;
import com.huuc.service.ConfessionStatusUpdateLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfessionStatusUpdateLogServiceImpl extends ServiceImpl<ConfessionStatusUpdateLogMapper, ConfessionStatusUpdateLog> implements ConfessionStatusUpdateLogService {
    private final ConfessionLogService confessionLogService;
    @Override
    @Transactional
    public void updateStatus(ConfessionStatusDTO confessionStatusDTO) {
        //1.先更新表白墙状态
        LambdaUpdateWrapper<ConfessionLog> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ConfessionLog::getId,confessionStatusDTO.getConfessionId())
                .set(ConfessionLog::getStatus,confessionStatusDTO.getNewStatus());
        confessionLogService.update(updateWrapper);
        //2.保存跟新记录
        this.save(
                ConfessionStatusUpdateLog
                .builder()
                .confessionId(confessionStatusDTO.getConfessionId())
                .updateUserId(BaseContext.getCurrentId())
                .oldStatus(confessionStatusDTO.getOldStatus())
                .newStatus(confessionStatusDTO.getNewStatus())
                .updateReason(confessionStatusDTO.getUpdateReason())
                .updateTime(LocalDateTime.now())
                .build());
    }
}
