package com.huuc.controller.user;

import com.huuc.context.BaseContext;
import com.huuc.pojo.Comment;
import com.huuc.result.AjaxResult;
import com.huuc.result.PageResult;
import com.huuc.service.CommentService;
import com.huuc.vo.CommentsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/comments")
@Api(tags = "评论相关接口")
@AllArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "comments")
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "新增评论")
    @PostMapping("/addComment")
    @CacheEvict(key = "#comment.getPostId()")
    public AjaxResult addComment(@RequestBody Comment comment) {
        comment.setUserId(BaseContext.getCurrentId());
        boolean save = commentService.save(comment);
        return save ? AjaxResult.success("评论成功") : AjaxResult.error("评论失败,请稍后再试");
    }

    @ApiOperation(value = "根据贴id查询评论")
    @Cacheable(key = "#postId")
    @GetMapping("/getComments/{id}")
    public AjaxResult getComments(@PathVariable("id") String postId) {
        List<CommentsVO> comments = commentService.getCommentsByPostId(postId);
        PageResult<CommentsVO> commentPage = new PageResult<>(comments.size(),comments);
        return AjaxResult.success(commentPage);
    }
}
