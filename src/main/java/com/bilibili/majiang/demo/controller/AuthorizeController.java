package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.AccessTokenDto;
import com.bilibili.majiang.demo.dto.GitHubUser;
import com.bilibili.majiang.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        String token = accessToken.split("&")[0].split("=")[1];
        GitHubUser gitHubUser = githubProvider.getGitHubUser(token);
        return "index";
    }

}
