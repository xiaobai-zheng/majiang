package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.dto.CommentDto;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.vo.Msg;

import java.util.List;

public interface CommentService {
    Msg submitComment(Comment comment);
    List<CommentDto> getQuestionComment(int parentId);
}
