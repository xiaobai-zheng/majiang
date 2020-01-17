package com.bilibili.majiang.demo.exception;

public class CustomException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomException(CustomExceptionCode customExceptionCode){
        this.message = customExceptionCode.getMessage();
        this.code = customExceptionCode.getCode();
    }
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
