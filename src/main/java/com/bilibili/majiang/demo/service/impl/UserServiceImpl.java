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
    public void insertUserByAidIsNull(User user) {
        if (userMapper.selectByAccountId(user.getAccountId())==null) {
            userMapper.insertSelective(user);
        }
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByAccountId(String accountId) {
        User user = userMapper.selectByAccountId(accountId);
        if (user !=null){
            return user;
        }
        return null;
    }

}
