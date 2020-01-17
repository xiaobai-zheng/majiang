package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.Comment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommentMapper extends Mapper<Comment> {

}
