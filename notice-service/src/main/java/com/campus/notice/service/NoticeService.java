package com.campus.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.notice.entity.Notice;

// IService<实体类>：MyBatis-Plus服务层通用接口，封装基础CRUD业务方法
public interface NoticeService extends IService<Notice> {
    // 暂无自定义业务方法，直接复用IService的方法
}
