package com.campus.order.feign;

import com.campus.order.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/api/user/get/{id}")
    R<?> getUserById(@PathVariable("id") Long id);
}