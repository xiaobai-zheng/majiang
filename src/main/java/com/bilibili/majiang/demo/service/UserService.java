package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.model.User;

public interface UserService {
    Long insertUserByAidIsNull(User user);
    User selectUserById(Long id);
    User selectByAccountId(String name);
}
