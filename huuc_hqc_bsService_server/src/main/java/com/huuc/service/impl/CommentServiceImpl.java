package com.huuc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.huuc.service.CommentService;
import com.huuc.pojo.Comment;
import com.huuc.mapper.CommentMapper;
import com.huuc.vo.CommentsVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private final SensitiveWordBs sensitiveWordBs;
    @Override
    public List<CommentsVO> getCommentsByPostId(String id,Integer postType) {
        List<CommentsVO> commentsVOList = baseMapper.getCommentsByPostId(id,postType);
        //遍历集合,用*代替敏感词
        for (CommentsVO commentsVO : commentsVOList) {
            commentsVO.setContent(sensitiveWordBs.replace(commentsVO.getContent()));
        }
        return commentsVOList;
    }
}
