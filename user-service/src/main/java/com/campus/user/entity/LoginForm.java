package com.campus.user.entity;

import lombok.Data;

/**
 * 登录请求参数实体
 */
@Data
public class LoginForm {
    // 用户名（前端传username）
    private String username;
    // 密码（前端传password）
    private String password;
}