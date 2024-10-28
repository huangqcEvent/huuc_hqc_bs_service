package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.ConfessionLogDTO;
import com.huuc.pojo.ConfessionLog;
import com.huuc.result.PageResult;
import com.huuc.vo.ConfessionDetailVO;
import com.huuc.vo.ConfessionLogVO;

public interface ConfessionLogService extends IService<ConfessionLog> {
    /**
     * 表白墙信息分页查询
     */
    PageResult<ConfessionLogVO> confessionPageQuery(ConfessionLogDTO confessionLogDTO);

    /**
     * 根据id表白墙详情
     */
    ConfessionDetailVO getDetailById(String id);
}
