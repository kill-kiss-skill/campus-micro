package com.campus.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.order.entity.OrderInfo;
import com.campus.order.feign.ProductFeignClient;
import com.campus.order.feign.UserFeignClient;
import com.campus.order.mapper.OrderMapper;
import com.campus.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public boolean createOrder(OrderInfo orderInfo) {
        // 1. 远程调用：查询用户是否存在
        userFeignClient.getUserById(orderInfo.getUserId());

        // 2. 远程调用：查询商品是否存在
        productFeignClient.getProductById(orderInfo.getProductId());

        // 3. 生成订单号
        orderInfo.setOrderNo(UUID.randomUUID().toString().replace("-", ""));

        // 4. 保存订单
        return save(orderInfo);
    }
}