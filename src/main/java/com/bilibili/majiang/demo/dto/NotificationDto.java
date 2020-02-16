package com.bilibili.majiang.demo.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Long id;
    private Long notifier;
    private Integer status;
    private Long outerid;
    private String type;
    private String notifierName;
    private String outerTitle;
}
