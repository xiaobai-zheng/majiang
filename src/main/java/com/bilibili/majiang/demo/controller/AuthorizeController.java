package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.AccessTokenDto;
import com.bilibili.majiang.demo.dto.GitHubUser;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.provider.GithubProvider;
import com.bilibili.majiang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        String token = accessToken.split("&")[0].split("=")[1];
        GitHubUser gitHubUser = githubProvider.getGitHubUser(token);
//        httpServletRequest.getSession().setAttribute("user",gitHubUser);
        if(gitHubUser != null){
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setToken(token);
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            user.setGemCreate(System.currentTimeMillis());
            user.setGemModified(System.currentTimeMillis());
            Long aLong = userService.insertUserByAidIsNull(user);
            if (aLong!=null){
                user.setId(aLong);
            }
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user",user);
//            httpServletResponse.addCookie(new Cookie("accountId",user.getAccountId()));
            String id = session.getId();
            Cookie cookie = new Cookie("JSESSIONID", id);
            cookie.setMaxAge(60*60*24);
            httpServletResponse.addCookie(cookie);
            return "redirect:/";
        }else {
            //登入失败的时候
            return "redirect:/";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().setAttribute("user",null);
        return "redirect:/";
    }

}
