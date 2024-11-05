package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.ConfessionStatusDTO;
import com.huuc.pojo.ConfessionStatusUpdateLog;


public interface ConfessionStatusUpdateLogService extends IService<ConfessionStatusUpdateLog> {

  /**
   * 表白墙更新状态
   */
    void updateStatus(ConfessionStatusDTO confessionStatusDTO);
}
