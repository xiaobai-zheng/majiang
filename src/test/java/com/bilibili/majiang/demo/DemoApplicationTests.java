package com.bilibili.majiang.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-SS");
//        System.out.println(date.getTime());
    }
}
