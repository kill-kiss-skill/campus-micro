package com.campus.user.entity;

import lombok.Data;

/**
 * 登录返回结果实体
 */
@Data
public class LoginVO {
    // 简易token（用用户ID+时间戳生成，替代JWT，适合毕业设计）
    private String token;
    // 用户基本信息
    private User user;
}