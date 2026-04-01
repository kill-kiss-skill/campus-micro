package com.campus.notice.common;

import lombok.Data;

@Data // Lombok注解，自动生成get/set/toString等方法，简化代码
public class R<T> {
    private int code;   // 状态码：200成功，500失败
    private String msg; // 提示信息
    private T data;     // 返回数据，泛型适配（单个对象、集合、布尔值等）

    // 成功返回：状态码200，提示“成功”，携带返回数据
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(200);
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    // 失败返回：状态码500，自定义提示信息，无返回数据
    public static <T> R<T> fail(String msg) {
        R<T> r = new R<>();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}