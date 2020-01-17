package com.bilibili.majiang.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, Throwable e, Model model){
//        Msg msg = null;
//        if("application/json".equals(httpServletRequest.getContentType())){
//            if (e instanceof CustomException) {
//               msg = Msg.fail((CustomException)e);
//            }else {
//                msg = Msg.fail();
//            }
//            try {
//                System.out.println("成功执行到了application/json请求,响应的url；"+httpServletResponse.getHeader("url"));
//                httpServletResponse.setContentType("application/json");
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.getWriter().write(JSON.toJSONString(msg));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            return null;
//        }else {
            if (e instanceof CustomException) {
                model.addAttribute("message", e.getMessage());
            }else {
                model.addAttribute("message","服务错误");
            }
            return new ModelAndView("error");
//        }

    }
}
