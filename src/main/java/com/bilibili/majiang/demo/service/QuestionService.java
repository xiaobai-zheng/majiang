package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.model.Question;
import com.github.pagehelper.PageInfo;

public interface QuestionService {
   void insertOrUpdateQuestion(Question question);
   PageInfo<QuestionDto> getPageInfo(Integer pn,Integer pageSize);
    PageInfo<QuestionDto> myQuestionspage(Integer Creator,Integer pn,Integer pageSize);

    QuestionDto selectQuestionById(Integer id);
}
