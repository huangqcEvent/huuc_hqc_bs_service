package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.UpdateStatusDTO;
import com.huuc.pojo.PostInteraction;

public interface PostInteractionService extends IService<PostInteraction> {
    void updateStatus(UpdateStatusDTO updateStatusDTO);
}
