package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.Enums.CustomTypeEnum;
import com.bilibili.majiang.demo.dto.CommentDto;
import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.service.CommentService;
import com.bilibili.majiang.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{questionId}")
    @Transactional
    public String question(@PathVariable(name = "questionId") Long questionId, Model model){
        QuestionDto questionDto = questionService.selectQuestionById(questionId);
        questionService.incViewCount(questionId);
        List<CommentDto> commentDtos = commentService.getComment(questionId, CustomTypeEnum.QUESTION_TYPE.getType());
        List<Question> questionLikes = questionService.selectTagLike(questionId, questionDto.getTag());
        model.addAttribute("questionLikes",questionLikes);
        model.addAttribute("questionDto",questionDto);
        model.addAttribute("commentDtos",commentDtos);
        return "question";
    }
    @GetMapping("/editQuestion/{id}")
    public  String editQuestion(@PathVariable(name = "id") Long id, Model model){
        QuestionDto questionDto = questionService.selectQuestionById(id);
        model.addAttribute("id",questionDto.getId());
        model.addAttribute("title",questionDto.getTitle());
        model.addAttribute("description",questionDto.getDescription());
        model.addAttribute("tag",questionDto.getTag());
        return "/publish";
    }
}
