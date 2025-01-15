package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.AuditPageDTO;
import com.huuc.pojo.PostAudit;
import com.huuc.result.PageResult;
import com.huuc.vo.AuditPageVO;

public interface PostAuditService extends IService<PostAudit> {
    PageResult<AuditPageVO> getPage(AuditPageDTO params);
}
