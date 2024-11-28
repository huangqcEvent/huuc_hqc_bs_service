package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.pojo.LostItem;
import com.huuc.result.PageResult;
import com.huuc.vo.LostItemPageVO;


public interface LostItemService extends IService<LostItem> {
    PageResult<LostItemPageVO> lostPage(LostItemPageDTO lostItemPageDTO);
}
