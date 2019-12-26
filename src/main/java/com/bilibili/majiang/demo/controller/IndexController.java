package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
//        Cookie[] cookies = httpServletRequest.getCookies();
//        for (Cookie cookie : cookies) {
//            String accountId = cookie.getName();
//            if (accountId.equals("accountId")){
//                User user = userService.selectByAccountId(accountId);
//                if (user !=null) {
//                    httpServletRequest.getSession().setAttribute("user", user);
//                    System.out.println("成功查询==》"+user);
//                }
//            }
//        }
        return "index";
    }
}
