package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.dto.UserPageDTO;
import com.huuc.pojo.User;
import com.huuc.result.PageResult;

public interface UserService extends IService<User> {
    /**
     * 员工信息分页查询
     */
    PageResult<User> getUserList(UserPageDTO userPageDTO);
}
