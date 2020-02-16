package com.bilibili.majiang.demo.Enums;

public enum NotificationStatusEnum {
    STATUS_UNREAD(1),
    STATUS_READ(2);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
