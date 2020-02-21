package com.bilibili.majiang.demo.exception;

public enum CustomExceptionCodeImpl implements CustomExceptionCode {
    FIND_QUESTION_EXCEPTION(201,"你找的问题不存在了，可以换一个问题试试"),
    FIND_PARENTID_EXCEPTION(202,"你要评论的问题或评论不存在了，可以换一个问题或评论试试"),
    FIND_TYPE_EXCEPTION(203,"你要评论的问题或评论的类型不存在，可以换一个问题或评论试试"),
    FIND_COMMENT_EXCEPTION(204,"你要回复的评论的类型不存在，可以换一个评论试试"),
    FIND_USER_EXCEPTION(205,"登入异常，请先登入然后再进行相关操作"),
    COMMENT_IS_EMPTY(206,"提交的评论的类容不能为空"),
    UCLOUD_FAIL(207,"上传图片失败");
    private Integer code;
    private String message;
    CustomExceptionCodeImpl(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
