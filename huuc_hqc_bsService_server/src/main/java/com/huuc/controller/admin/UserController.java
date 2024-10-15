package com.huuc.controller.admin;

import com.huuc.dto.UserPageDTO;
import com.huuc.pojo.User;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
@Api(tags = "管理员用户管理相关接口")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "员工信息分页查询")
    @GetMapping("/userPage")
    public AjaxResult getUserList(UserPageDTO userPageDTO) {
        log.info("员工信息分页查询:{}", userPageDTO);
        PageResult<User> pageResult = userService.getUserList(userPageDTO);
        return AjaxResult.success(pageResult);
    }
}
