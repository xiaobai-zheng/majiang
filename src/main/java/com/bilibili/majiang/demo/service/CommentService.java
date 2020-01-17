package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.vo.Msg;

public interface CommentService {
    Msg submitComment(Comment comment);
}
