package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.model.Question;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QuestionService {
   void insertOrUpdateQuestion(Question question);
   PageInfo<QuestionDto> getPageInfo(Integer pn,Integer pageSize);
    PageInfo<QuestionDto> searchQuestion(String search,Integer pn,Integer pageSize);
    PageInfo<QuestionDto> myQuestionspage(Long Creator, Integer pn, Integer pageSize);
    QuestionDto selectQuestionById(Long id);
    int incViewCount(Long id);
    int incCommentCount(Long id);
    List<Question> selectTagLike(Long id, String tag);
}
