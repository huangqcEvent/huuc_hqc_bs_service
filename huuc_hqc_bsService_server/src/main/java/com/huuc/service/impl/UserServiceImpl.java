package com.huuc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.context.BaseContext;
import com.huuc.dto.*;
import com.huuc.mapper.UserMapper;
import com.huuc.pojo.DownLog;
import com.huuc.pojo.User;
import com.huuc.pojo.UserIdentityUpdateLog;
import com.huuc.result.PageResult;
import com.huuc.service.DownLogService;
import com.huuc.service.UserIdentityUpdateLogService;
import com.huuc.service.UserService;
import com.huuc.vo.DownLogVo;
import com.huuc.vo.IdentityUpdateVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final DownLogService downLogService;
    private final UserIdentityUpdateLogService userIdentityUpdateLogService;
    @Override
    public PageResult<User> getUserList(UserPageDTO userPageDTO) {
        //1.设置分页
        PageHelper.startPage(userPageDTO.getPageNumber(), userPageDTO.getPageSize());
        //2.执行查询
        Page<User> userPage = (Page<User>) baseMapper.userPage(userPageDTO);
        //3.封装结果
        return new PageResult<>(userPage.getTotal(), userPage.getResult());
    }

    @Override
    public PageResult<DownLogVo> getDownLogList(DownLogPageDTO downLogPageDTO) {
        //1.设置分页
        PageHelper.startPage(downLogPageDTO.getPageNumber(), downLogPageDTO.getPageSize());
        //2.执行查询
        Page<DownLogVo> downLogPage = (Page<DownLogVo>) baseMapper.downLogPage(downLogPageDTO);
        //3.封装结果
        return new PageResult<>(downLogPage.getTotal(), downLogPage.getResult());
    }


    @Override
    public PageResult<IdentityUpdateVO> getIdentityLog(IdentityPageDTO identityPageDto) {
        //1.设置分页
        PageHelper.startPage(identityPageDto.getPageNumber(), identityPageDto.getPageSize());
        //2.执行查询
        Page<IdentityUpdateVO> identityLogPage = (Page<IdentityUpdateVO>) baseMapper.identityLogPage(identityPageDto);
        //3.封装结果
        return new PageResult<>(identityLogPage.getTotal(), identityLogPage.getResult());
    }

    @Override
    @Transactional
    public void updateUserState(DownUserDTO downUserDTO) {
        // 1. 更新用户的封禁状态
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, downUserDTO.getId())
                .set(User::getState, downUserDTO.getState());
        update(updateWrapper);

        // 2. 处理封禁记录
        if (downUserDTO.getState() == 0) {
            // 新增一条封禁记录
            DownLog downLog = DownLog.builder()
                    .userId(downUserDTO.getId())
                    .downReason(downUserDTO.getDownReason())
                    .downAdminId(BaseContext.getCurrentId())
                    .downEnd(0)
                    .downTime(LocalDateTime.now())
                    .build();
            downLogService.save(downLog);
        } else if (downUserDTO.getState() == 1) {
            // 更新此用户封禁记录里为封禁中的记录
            LambdaUpdateWrapper<DownLog> downLogUpdateWrapper = new LambdaUpdateWrapper<>();
            downLogUpdateWrapper.eq(DownLog::getUserId, downUserDTO.getId())
                    .eq(DownLog::getDownEnd, 0)
                    .set(DownLog::getDownEnd, 1)
                    .set(DownLog::getDownEndAdminId, BaseContext.getCurrentId())
                    .set(DownLog::getDownEndTime, LocalDateTime.now());
            downLogService.update(downLogUpdateWrapper);
        }
    }

    @Override
    @Transactional
    public void updateIdentity(UpdateIdentityDTO updateIdentity) {
        //1.更新用户身份
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId,updateIdentity.getUserId())
                .eq(User::getIdentity,updateIdentity.getOldIdentity())
                .set(User::getIdentity,updateIdentity.getNewIdentity());
        update(lambdaUpdateWrapper);

        //2.新增更新记录
        userIdentityUpdateLogService.save(
                UserIdentityUpdateLog.builder()
                        .userId(updateIdentity.getUserId())
                        .oldIdentity(updateIdentity.getOldIdentity())
                        .newIdentity(updateIdentity.getNewIdentity())
                        .updateAdminId(BaseContext.getCurrentId())
                        .updateTime(LocalDateTime.now())
                        .updateReason(updateIdentity.getUpdateReason())
                        .build()
        );
    }
}
