package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.service.QuestionService;
import org.junit.Test;

public class QuestionServiceImpTest {

    @Test
    public void selectQuestionById() {
        QuestionService questionService = new QuestionServiceImp();
        QuestionDto questionDto = questionService.selectQuestionById(2L);
    }
}