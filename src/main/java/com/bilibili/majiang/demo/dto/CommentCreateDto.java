package com.bilibili.majiang.demo.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private String  content;
    private Long parentId;
    private Integer type;
}
