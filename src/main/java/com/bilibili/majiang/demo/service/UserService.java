package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.model.User;

public interface UserService {
    Integer insertUserByAidIsNull(User user);
    User selectUserById(Integer id);
    User selectByAccountId(String name);
}
