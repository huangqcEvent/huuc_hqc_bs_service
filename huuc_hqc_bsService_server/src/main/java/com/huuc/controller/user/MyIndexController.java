package com.huuc.controller.user;

import com.huuc.dto.MyPostPageDTO;
import com.huuc.result.AjaxResult;
import com.huuc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/myIndex")
@Slf4j
@Api(tags = "用户首页相关接口")
@AllArgsConstructor
public class MyIndexController {
    private final UserService userService;

    @GetMapping("/getAllPost")
    @ApiOperation("/词云返回")
    public AjaxResult getAllPost(){
        return AjaxResult.success(userService.getAllPost());
    }

    @PostMapping("/myPostPage")
    @ApiOperation("/我的帖子分页")
    public AjaxResult myPostPage(@RequestBody MyPostPageDTO myPostPageDTO){
        return AjaxResult.success(userService.getMyPostPage(myPostPageDTO));
    }

    @PostMapping("/myLiked")
    @ApiOperation("/我的点赞")
    public AjaxResult myLiked(@RequestBody MyPostPageDTO myPostPageDTO){
        return AjaxResult.success(userService.getMyLiked(myPostPageDTO));
    }

    @PostMapping("/myFavored")
    @ApiOperation("/我的收藏")
    public AjaxResult MyFavored(@RequestBody MyPostPageDTO myPostPageDTO){
        return AjaxResult.success(userService.getMyFavored(myPostPageDTO));
    }

    @PostMapping("/myComment")
    @ApiOperation("/我的评论")
    public AjaxResult myComment(@RequestBody MyPostPageDTO myPostPageDTO){
        return AjaxResult.success(userService.getMyComment(myPostPageDTO));
    }
}
