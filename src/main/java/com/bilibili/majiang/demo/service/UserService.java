package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.model.User;

public interface UserService {
    void inserUser(User user);
    User selectUserById(Integer id);
}
