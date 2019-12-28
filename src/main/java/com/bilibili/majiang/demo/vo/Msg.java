package com.bilibili.majiang.demo.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class Msg {
    //状态码，100表示成功 200表示失败
    private int code;
    private String msg;
    private Map<String,Object> extend = new HashMap<>();
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("恭喜您，您的操作成功了");
        return result;
    }
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("操作失败");
        return result;
    }
    public Msg add(String key, Object object){
        this.getExtend().put(key,object);
        return this;
    }
}
