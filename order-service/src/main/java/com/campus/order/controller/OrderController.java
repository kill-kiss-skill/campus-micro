package com.campus.order.controller;

import com.campus.order.common.R;
import com.campus.order.entity.OrderInfo;
import com.campus.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单（核心接口：跨服务调用）
    @PostMapping("/create")
    public R<Boolean> createOrder(@RequestBody OrderInfo orderInfo) {
        boolean result = orderService.createOrder(orderInfo);
        return R.ok(result);
    }

    // 查询所有订单
    @GetMapping("/list")
    public R<List<OrderInfo>> list() {
        List<OrderInfo> list = orderService.list();
        return R.ok(list);
    }

    // 根据id查询
    @GetMapping("/get/{id}")
    public R<OrderInfo> getById(@PathVariable Long id) {
        OrderInfo orderInfo = orderService.getById(id);
        return R.ok(orderInfo);
    }

    // 删除订单
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean remove = orderService.removeById(id);
        return R.ok(remove);
    }
}