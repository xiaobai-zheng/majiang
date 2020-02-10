package com.bilibili.majiang.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDto {
    private String categoryName;
    private List<String> tags;
}
