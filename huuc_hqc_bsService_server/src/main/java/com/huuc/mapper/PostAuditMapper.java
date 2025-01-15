package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.AuditPageDTO;
import com.huuc.pojo.PostAudit;
import com.huuc.vo.AuditPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostAuditMapper extends BaseMapper<PostAudit> {
    List<AuditPageVO> getPage(AuditPageDTO params);
}
