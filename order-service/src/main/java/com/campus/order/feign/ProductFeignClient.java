package com.campus.order.feign;

import com.campus.order.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductFeignClient {

    @GetMapping("/product/get/{id}")
    R<?> getProductById(@PathVariable("id") Long id);
}