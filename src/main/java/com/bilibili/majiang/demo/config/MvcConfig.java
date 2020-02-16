package com.bilibili.majiang.demo.config;

import com.bilibili.majiang.demo.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public SessionInterceptor getSessionInterceptor(){
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getSessionInterceptor()).addPathPatterns("/*").excludePathPatterns("/","/callback");
    }
}
