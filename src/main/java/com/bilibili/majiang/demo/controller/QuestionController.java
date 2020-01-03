package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{questionId}")
    public String question(@PathVariable(name = "questionId") Integer questionId, Model model){
        QuestionDto questionDto = questionService.selectQuestionById(questionId);
        model.addAttribute("questionDto",questionDto);
        return "question";
    }
    @GetMapping("/editQuestion/{id}")
    public  String editQuestion(@PathVariable(name = "id") Integer id,Model model){
        QuestionDto questionDto = questionService.selectQuestionById(id);
        model.addAttribute("id",questionDto.getId());
        model.addAttribute("title",questionDto.getTitle());
        model.addAttribute("description",questionDto.getDescription());
        model.addAttribute("tag",questionDto.getTag());
        return "/publish";
    }
}
