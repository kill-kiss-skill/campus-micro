package com.campus.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.notice.entity.Notice;
import com.campus.notice.mapper.NoticeMapper;
import com.campus.notice.service.NoticeService;
import org.springframework.stereotype.Service;

@Service // 声明为Spring业务层组件，被容器扫描并注入
// ServiceImpl<Mapper接口, 实体类>：MyBatis-Plus服务层通用实现类
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    // 无需手动编写实现代码，ServiceImpl已实现IService的所有方法，底层调用Mapper的方法
}
