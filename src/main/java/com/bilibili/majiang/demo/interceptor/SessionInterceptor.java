package com.bilibili.majiang.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.NotificationService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private NotificationService notificationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(JSON.toJSONString(Msg.fail(CustomExceptionCodeImpl.FIND_USER_EXCEPTION)));
            return false;
        }
        session.setAttribute("unReadCount",notificationService.unReadCount(user.getId()));
        System.out.println("成功通过");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
