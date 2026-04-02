package com.campus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.user.entity.LoginForm;
import com.campus.user.entity.LoginVO;
import com.campus.user.entity.User;

public interface UserService extends IService<User> {
    // 新增登录方法
    LoginVO login(LoginForm loginForm);
}