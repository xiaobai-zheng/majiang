package com.bilibili.majiang.demo.dto;

import com.bilibili.majiang.demo.model.Comment;
import lombok.Data;

@Data
public class CommentDto extends Comment {
    private String name;
    private String avatarUrl;
}
