package com.bilibili.majiang.demo.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String  content;
    private Integer parentId;
    private Integer type;
}
