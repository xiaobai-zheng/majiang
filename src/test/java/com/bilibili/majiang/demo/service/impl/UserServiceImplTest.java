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
    public void insertUserByAidIsNull() {
        User user = new User();
        user.setAccountId("1");
        user.setName("张三");
        user.setToken(UUID.randomUUID().toString());
        user.setGemCreate(System.currentTimeMillis());
        user.setGemModified(System.currentTimeMillis());
        userService.insertUserByAidIsNull(user);
        System.out.println("新插入的user的Id="+user.getId());
    }
    @Test
    public void selectUserById() {
        System.out.println(userService.selectUserById(1));
    }
    @Test
    public void selectUserByAccountId(){
        System.out.println("你好；"+userService.selectByAccountId("ffb20d76b536e9fa082b7ae3c3cf46a07a0e238f"));
    }
}