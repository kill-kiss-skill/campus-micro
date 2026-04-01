package com.campus.notice.controller;

import com.campus.notice.common.R;
import com.campus.notice.entity.Notice;
import com.campus.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 新增公告
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody Notice notice) {
        notice.setCreateTime(LocalDateTime.now());
        boolean save = noticeService.save(notice);
        return R.ok(save);
    }

    // 删除公告
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean remove = noticeService.removeById(id);
        return R.ok(remove);
    }

    // 修改公告
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Notice notice) {
        boolean update = noticeService.updateById(notice);
        return R.ok(update);
    }

    // 根据id查询
    @GetMapping("/get/{id}")
    public R<Notice> getById(@PathVariable Long id) {
        Notice notice = noticeService.getById(id);
        return R.ok(notice);
    }

    // 查询所有公告
    @GetMapping("/list")
    public R<List<Notice>> list() {
        List<Notice> list = noticeService.list();
        return R.ok(list);
    }
}
