package com.huuc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huuc.pojo.Comment;
import com.huuc.vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据帖子id查询评论
     */
    List<CommentsVO> getCommentsByPostId(String id);
}
