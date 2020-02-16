package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.NotificationDto;
import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.NotificationService;
import com.bilibili.majiang.demo.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{section}")
    public String profile(@PathVariable(name = "section") String section, HttpServletRequest httpServletRequest,Model model){
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        if (section == null||"questions".equals(section)){
            PageInfo<QuestionDto> pageInfo = questionService.myQuestionspage(user.getId(),1,6);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if ("replies".equals(section)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            PageInfo<NotificationDto> notificationDtoPageInfo = notificationService.myNotificationpage(user.getId(), 1, 6);
            model.addAttribute("pageInfo",notificationDtoPageInfo);
        }
        return "profile";
    }

}
