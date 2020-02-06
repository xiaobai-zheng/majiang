package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.exception.CustomException;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
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
import tk.mybatis.mapper.entity.Example;
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
        Long id = question.getId();
        Question question1 = questionMapper.selectByPrimaryKey(id);
        if (question1 != null){
            Example example = new Example(Question.class);
            example.createCriteria().andEqualTo("id",id);
            questionMapper.updateByExampleSelective(question,example);
        }else {
            questionMapper.insertSelective(question);
        }
    }

    @Override
    public PageInfo<QuestionDto> getPageInfo(Integer pn,Integer pageSize) {
        PageHelper.startPage(pn,pageSize,true);
        List<Question> questions = questionMapper.selectAll();
        PageInfo<Question> pageInfo = new PageInfo<>(questions, 5);
        PageInfo<QuestionDto> pageInfo1 = new PageInfo<>();
        List<QuestionDto> questionDtos = this.questionDtoList(questions);
        BeanUtils.copyProperties(pageInfo,pageInfo1);
        pageInfo1.setList(questionDtos);
        return pageInfo1;
    }

    @Override
    public PageInfo<QuestionDto> myQuestionspage(Long creator, Integer pn, Integer pageSize) {
        PageHelper.startPage(pn,pageSize,true);
        Example example = new Example(Question.class);
        example.createCriteria().andEqualTo("creator",creator);
        List<Question> questions = questionMapper.selectByExample(example);
        PageInfo<Question> pageInfo = new PageInfo<>(questions, 5);
        List<QuestionDto> questionDtos = this.questionDtoList(questions);
        PageInfo<QuestionDto> pageInfo1 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo,pageInfo1);
        pageInfo1.setList(questionDtos);
        return pageInfo1;
    }

    @Override
    public QuestionDto selectQuestionById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomException(CustomExceptionCodeImpl.FIND_QUESTION_EXCEPTION);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        if (user != null){
            questionDto.setAvatarUrl(user.getAvatarUrl());
            questionDto.setCreatorName(user.getName());
        }
        return questionDto;
    }

    @Override
    public int incViewCount(Long id) {
        return questionMapper.incViewCount(id);
    }

    @Override
    public int incCommentCount(Long id) {
        return questionMapper.incCommentCount(id);
    }

}
