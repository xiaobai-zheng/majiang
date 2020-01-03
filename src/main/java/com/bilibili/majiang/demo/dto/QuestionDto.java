package com.bilibili.majiang.demo.dto;

import com.bilibili.majiang.demo.model.Question;
import lombok.Data;

@Data
public class QuestionDto extends Question {
    private String creatorName;
    private String avatarUrl;
}
