package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(@RequestParam(value = "pn",defaultValue = "1")Integer pn,
                        @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,Model model){
        PageInfo<QuestionDto> pageInfo = questionService.getPageInfo(pn,pageSize);
//        Msg msg = Msg.success().add("pageInfo", paageInfo);
//        System.out.println(pageInfo);
         model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
