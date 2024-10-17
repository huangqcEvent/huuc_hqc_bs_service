package com.huuc.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huuc.context.BaseContext;
import com.huuc.dto.DownLogPageDTO;
import com.huuc.dto.DownUserDTO;
import com.huuc.dto.UserPageDTO;
import com.huuc.mapper.UserMapper;
import com.huuc.pojo.DownLog;
import com.huuc.pojo.User;
import com.huuc.result.PageResult;
import com.huuc.service.DownLogService;
import com.huuc.service.UserService;
import com.huuc.vo.DownLogVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final DownLogService downLogService;
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
    public void downUser(DownUserDTO downUserDTO) {
        //1.将用户的封禁状态设置为0
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, downUserDTO.getId())
                .set(User::getState, downUserDTO.getState());
        update(updateWrapper);

        //2.新增一条封禁记录
        DownLog downLog = DownLog.builder()
                .userId(downUserDTO.getId())
                .downReason(downUserDTO.getDownReason())
                .downAdminId(BaseContext.getCurrentId())
                .downEnd(downUserDTO.getState())
                .downTime(LocalDateTime.now())
                .build();
        downLogService.save(downLog);
    }

    @Override
    public void downEnd(DownUserDTO downUserDTO) {
        //1.将用户的封禁状态设置为1
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, downUserDTO.getId())
                .set(User::getState, downUserDTO.getState());
        update(updateWrapper);
        //2.更新此用户封禁记录里为封禁中的记录
        LambdaUpdateWrapper<DownLog> downLogUpdateWrapper = new LambdaUpdateWrapper<>();
        downLogUpdateWrapper.eq(DownLog::getUserId, downUserDTO.getId())
                .eq(DownLog::getDownEnd, 0)
                .set(DownLog::getDownEnd, downUserDTO.getState())
                .set(DownLog::getDownEndAdminId, BaseContext.getCurrentId())
                .set(DownLog::getDownEndTime, LocalDateTime.now());
        downLogService.update(downLogUpdateWrapper);
    }
}
