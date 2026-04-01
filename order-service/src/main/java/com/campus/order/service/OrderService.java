package com.campus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.order.entity.OrderInfo;

public interface OrderService extends IService<OrderInfo> {
    // 创建订单（演示跨服务调用）
    boolean createOrder(OrderInfo orderInfo);
}