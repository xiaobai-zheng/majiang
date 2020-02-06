package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.QuestionService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @Transactional
    @PostMapping("/publish")
    public String doPublish(Long id, String title, String description, String tag, HttpServletRequest httpServletRequest, Model model){
        if (title ==null||title==""||description  == null||description==""||tag == null||tag==""){
              Msg msg = Msg.fail();
              Map<String, String> errorMap = new HashMap();
              if (title == null||title==""){
                  errorMap.put("titleError","标题不能为空,");
              }
              if (description  == null||description==""){
                  errorMap.put("descriptionError","问题描述不能为空,");
              }
              if (tag == null||tag==""){
                  errorMap.put("tagError","标签不能为空,");
              }
                model.addAttribute("title",title);
                model.addAttribute("description",description);
                model.addAttribute("tag",tag);
                model.addAttribute("id",id);
                msg.add("errorMap",errorMap);
                model.addAttribute("msg",msg);
                 return "publish";
           }
            HttpSession session = httpServletRequest.getSession();
            User user = (User)session.getAttribute("user");
            Question question = new Question();
            question.setId(id);
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGemCreate(System.currentTimeMillis());
            question.setGemModified(question.getGemCreate());
            questionService.insertOrUpdateQuestion(question);
            return "redirect:/?pn="+Integer.MAX_VALUE;
    }
}
