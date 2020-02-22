package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.QuestionDto;
import com.bilibili.majiang.demo.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
                        @RequestParam(value = "pageSize",defaultValue = "8") Integer pageSize,
                        @RequestParam(value = "search",required = false)String search,Model model){
        if (StringUtils.isNotBlank(search)){
            PageInfo<QuestionDto> pageInfo = questionService.searchQuestion(search, pn, pageSize);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("search",search);
            return "index";
        }else {
            PageInfo<QuestionDto> pageInfo = questionService.getPageInfo(pn,pageSize);
            model.addAttribute("pageInfo",pageInfo);
            return "index";
        }
    }
}
