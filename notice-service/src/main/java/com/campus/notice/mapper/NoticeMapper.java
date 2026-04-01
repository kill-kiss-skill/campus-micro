package com.campus.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 声明为MyBatis Mapper接口，也可通过启动类@MapperScan扫描
public interface NoticeMapper extends BaseMapper<Notice> {
    // 无自定义方法，直接继承BaseMapper的所有方法，如insert、deleteById、updateById、selectById、selectList等
}
