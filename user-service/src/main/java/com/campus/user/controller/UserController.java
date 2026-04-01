package com.campus.user.controller;

import com.campus.user.common.R;
import com.campus.user.entity.User;
import com.campus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 新增用户
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody User user) {
        boolean save = userService.save(user);
        return R.ok(save);
    }

    // 根据ID删除
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean remove = userService.removeById(id);
        return R.ok(remove);
    }

    // 修改用户
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return R.ok(update);
    }

    // 根据ID查询
    @GetMapping("/get/{id}")
    public R<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return R.ok(user);
    }

    // 查询所有用户
    @GetMapping("/list")
    public R<List<User>> list() {
        List<User> list = userService.list();
        return R.ok(list);
    }
}
