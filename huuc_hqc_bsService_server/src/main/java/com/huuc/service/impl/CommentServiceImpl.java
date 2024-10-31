package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huuc.service.CommentService;
import com.huuc.pojo.Comment;
import com.huuc.mapper.CommentMapper;
import com.huuc.vo.CommentsVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public List<CommentsVO> getCommentsByPostId(String id) {
        return baseMapper.getCommentsByPostId(id);
    }
}
