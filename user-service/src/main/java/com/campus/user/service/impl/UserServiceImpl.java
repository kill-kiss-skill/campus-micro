package com.campus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.user.entity.LoginForm;
import com.campus.user.entity.LoginVO;
import com.campus.user.entity.User;
import com.campus.user.mapper.UserMapper;
import com.campus.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public LoginVO login(LoginForm loginForm) {
        // 1. 校验参数
        if (!StringUtils.hasText(loginForm.getUsername()) || !StringUtils.hasText(loginForm.getPassword())) {
            throw new RuntimeException("用户名或密码不能为空");
        }

        // 2. 根据用户名查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginForm.getUsername());
        User user = this.getOne(queryWrapper);

        // 3. 校验用户是否存在+密码是否正确
        if (user == null || !user.getPassword().equals(loginForm.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 4. 生成简易token（毕业设计够用，替代复杂JWT）
        String token = UUID.randomUUID().toString().replace("-", "") + "_" + user.getId();

        // 5. 封装返回结果
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUser(user);

        return loginVO;
    }
}