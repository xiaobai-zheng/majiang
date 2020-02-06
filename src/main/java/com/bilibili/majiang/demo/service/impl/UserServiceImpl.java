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
    public Long insertUserByAidIsNull(User user) {
        User user1 = userMapper.selectByAccountId(user.getAccountId());
        if (user1==null) {
            userMapper.insertSelective(user);
            return null;
        }
        Long id = user1.getId();
        user.setId(id);
        userMapper.updateByPrimaryKeySelective(user);
        return id;
    }

    @Override
    public User selectUserById(Long id) {
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
