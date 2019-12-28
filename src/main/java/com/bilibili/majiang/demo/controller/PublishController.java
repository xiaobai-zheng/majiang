package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.model.Publish;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.PublishService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private PublishService publishService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(String title,String description,String tag, HttpServletRequest httpServletRequest,Model model){
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("user;;"+user);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

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
              System.out.println("存在错误==>"+errorMap);
               msg.add("errorMap",errorMap);
               model.addAttribute("publishMsg",msg);
                 return "publish";
           }
            Publish publish = new Publish();
            publish.setTitle(title);
            publish.setDescription(description);
            publish.setTag(tag);
            publish.setCreator(0);
            publish.setGemCreate(System.currentTimeMillis());
            publish.setGemModified(publish.getGemCreate());
            publishService.insertPublish(publish);
            return "redirect:/";
    }
}
