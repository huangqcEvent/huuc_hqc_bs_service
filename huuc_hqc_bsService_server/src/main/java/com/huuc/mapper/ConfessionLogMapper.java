package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.dto.ConfessionLogDTO;
import com.huuc.pojo.ConfessionLog;
import com.huuc.vo.ConfessionLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfessionLogMapper extends BaseMapper<ConfessionLog> {
    /**
     * 表白信息小卡片分页查询
     */
    List<ConfessionLogVO> confessionPageQuery(ConfessionLogDTO confessionLogDTO);
}
