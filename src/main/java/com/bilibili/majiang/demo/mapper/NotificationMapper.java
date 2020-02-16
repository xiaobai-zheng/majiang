package com.bilibili.majiang.demo.mapper;

import com.bilibili.majiang.demo.model.Notification;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface NotificationMapper extends Mapper<Notification> {
}
