package com.bilibili.majiang.demo;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.bilibili.majiang.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
    }
}
