package com.huuc.service.detail.module;

import com.huuc.service.UserService;
import com.huuc.service.detail.DetailParams;
import com.huuc.service.detail.GetDetailModule;
import com.huuc.service.detail.data.UserInfoData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserInfoModule implements GetDetailModule<UserInfoData> {
    @Resource
    private UserService userService;

    @Override
    public UserInfoData getData(DetailParams params) {
        return userService.getInfoById(params.getUserId());
    }
}
