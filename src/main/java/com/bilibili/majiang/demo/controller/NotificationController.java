package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.Enums.NotificationTypeEnum;
import com.bilibili.majiang.demo.model.Notification;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/notification/{id}/{outerid}")
    public String read(@PathVariable(name = "id") Long id, @PathVariable(name = "outerid") Long outerid, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        Notification notification = notificationService.read(id);
        if (notification != null &&notification.getType() == NotificationTypeEnum.REPLY_QUESTION.getType() || notification.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
            return "redirect:/question/"+outerid;
        }else {
            return "redirect:/";
        }
    }
}
