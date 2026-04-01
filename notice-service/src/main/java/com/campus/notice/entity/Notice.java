package com.campus.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok：自动生成get/set等方法
@TableName("notice") // MyBatis-Plus：指定映射的数据库表名
public class Notice {
    @TableId(type = IdType.AUTO) // 主键注解，指定自增策略（与yml中配置一致）
    private Long id;         // 公告ID，主键自增
    private String title;    // 公告标题
    private String content;  // 公告内容
    private LocalDateTime createTime; // 公告创建时间，对应数据库create_time
}
