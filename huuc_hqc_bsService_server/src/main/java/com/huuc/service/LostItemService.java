package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.LostItemPageDTO;
import com.huuc.pojo.LostItem;
import com.huuc.result.PageResult;
import com.huuc.service.detail.data.LostItemInfoData;
import com.huuc.vo.LostItemDetailVO;
import com.huuc.vo.LostItemPageVO;


public interface LostItemService extends IService<LostItem> {
    PageResult<LostItemPageVO> lostPage(LostItemPageDTO lostItemPageDTO);

    LostItemDetailVO getDetailById(String id);

    LostItemInfoData getDetailById1(String id);
}
