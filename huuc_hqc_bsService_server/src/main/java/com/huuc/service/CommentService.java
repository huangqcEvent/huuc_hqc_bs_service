package com.huuc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huuc.pojo.Comment;
import com.huuc.vo.CommentsVO;

import java.util.List;

public interface CommentService extends IService<Comment> {
    /**
     * 根据贴id查询所有评论
     */
    List<CommentsVO> getCommentsByPostId(String id,Integer postType);
}
