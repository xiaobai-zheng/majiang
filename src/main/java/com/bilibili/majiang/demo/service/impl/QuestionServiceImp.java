package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.mapper.QuestionMapper;
import com.bilibili.majiang.demo.mapper.UserMapper;
import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDto> questionDtoList(List<Question> questions){
        ArrayList<QuestionDto> questionDtos = new ArrayList<>();
        if(questions != null) {
            for (Question question : questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(question, questionDto);
                if(user != null) {
                    questionDto.setAvatarUrl(user.getAvatarUrl());
                }
                questionDtos.add(questionDto);
            }
        }
        return questionDtos;
    }
    @Override
    public void insertOrUpdateQuestion(Question question) {
        Integer id = question.getId();
        System.out.println("id===>>"+id);
        Question question1 = questionMapper.selectQuesionById(id);
        if (question1 != null){
            questionMapper.updateByPrimaryKey(question);
        }else {
            System.out.println("question1==null");
            questionMapper.insertSelective(question);
        }
    }

    @Override
    public PageInfo<QuestionDto> getPageInfo(Integer pn,Integer pageSize) {
        List<Question> questions = questionMapper.selectAll();
        List<QuestionDto> questionDtos = this.questionDtoList(questions);
        PageHelper.startPage(pn,pageSize);
//        System.out.println("questionDtos的长度；"+questionDtos.size());
        PageInfo<QuestionDto> pageInfo = new PageInfo<>(questionDtos, 5);
        return pageInfo;
    }

    @Override
    public PageInfo<QuestionDto> myQuestionspage(Integer Creator,Integer pn,Integer pageSize) {
        List<Question> questions = questionMapper.selectQuesionByCreator(Creator);
        System.out.println("questions"+questions);
        List<QuestionDto> questionDtos = this.questionDtoList(questions);
        PageHelper.startPage(pn,pageSize);
        PageInfo<QuestionDto> pageInfo = new PageInfo<>(questionDtos, 5);
        return pageInfo;
    }

    @Override
    public QuestionDto selectQuestionById(Integer id) {
        Question question = questionMapper.selectQuesionById(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        if (user != null){
            questionDto.setAvatarUrl(user.getAvatarUrl());
            questionDto.setCreatorName(user.getName());
        }
        return questionDto;
    }
}
