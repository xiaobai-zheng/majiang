package com.bilibili.majiang.demo.Enums;

public enum CustomTypeEnum {
    QUESTION_TYPE(1),
    COMMENT_TYPE(2);
    private Integer type;

    CustomTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExit(Integer type){
        for (CustomTypeEnum customTypeEnum : CustomTypeEnum.values()) {
            if (customTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
