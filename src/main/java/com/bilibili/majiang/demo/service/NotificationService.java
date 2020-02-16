package com.bilibili.majiang.demo.service;

import com.bilibili.majiang.demo.dto.NotificationDto;
import com.bilibili.majiang.demo.model.Notification;
import com.github.pagehelper.PageInfo;

public interface NotificationService {
    PageInfo<NotificationDto> myNotificationpage(Long id, int pn, int pageSize);

    Notification read(Long id);

    Integer unReadCount(Long id);
}
