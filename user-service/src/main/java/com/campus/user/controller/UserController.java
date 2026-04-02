package com.campus.user.controller;

import com.campus.user.common.R;
import com.campus.user.entity.LoginForm;
import com.campus.user.entity.LoginVO;
import com.campus.user.entity.User;
import com.campus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ========== 新增登录接口 ==========
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody LoginForm loginForm) {
        try {
            LoginVO loginVO = userService.login(loginForm);
            return R.ok(loginVO);
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }

    // ========== 原有接口全部保留 ==========
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody User user) {
        boolean save = userService.save(user);
        return R.ok(save);
    }

    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean remove = userService.removeById(id);
        return R.ok(remove);
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return R.ok(update);
    }

    @GetMapping("/get/{id}")
    public R<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return R.ok(user);
    }

    @GetMapping("/list")
    public R<List<User>> list() {
        List<User> list = userService.list();
        return R.ok(list);
    }
}