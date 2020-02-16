package com.bilibili.majiang.demo.Enums;

public enum NotificationTypeEnum {

    REPLY_QUESTION(1,"评论了问题"),
    REPLY_COMMENT(2,"评论了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String typeOfName(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.type == type){
                return notificationTypeEnum.name;
            }
        }
        return "";
    }
}
