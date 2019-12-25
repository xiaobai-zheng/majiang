package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void inserUser() {
        User user = new User();
        user.setAccountId("1");
        user.setName("张三");
        user.setToken(UUID.randomUUID().toString());
        user.setGemCreate(System.currentTimeMillis());
        user.setGemModified(System.currentTimeMillis());
        userService.inserUser(user);
        System.out.println("新插入的user的Id="+user.getId());
    }
    @Test
    public void selectUserById() {
        System.out.println(userService.selectUserById(1));
    }
}