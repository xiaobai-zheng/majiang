package com.bilibili.majiang.demo.config;

import com.bilibili.majiang.demo.interceptor.PublishInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//记得加上@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public PublishInterceptor getPublishInterceptor(){
        return new PublishInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getPublishInterceptor()).addPathPatterns("/publish");
    }
}
