package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.mapper.UserMapper;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void inserUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
