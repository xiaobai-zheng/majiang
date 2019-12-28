package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.mapper.PublishMapper;
import com.bilibili.majiang.demo.model.Publish;
import com.bilibili.majiang.demo.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImp implements PublishService {
    @Autowired
    private PublishMapper publishMapper;
    @Override
    public void insertPublish(Publish publish) {
        publishMapper.insertSelective(publish);
    }
}
