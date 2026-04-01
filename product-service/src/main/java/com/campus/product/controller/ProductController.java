package com.campus.product.controller;

import com.campus.product.common.R;
import com.campus.product.entity.Product;
import com.campus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 新增商品
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody Product product) {
        boolean save = productService.save(product);
        return R.ok(save);
    }

    // 删除商品
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        boolean remove = productService.removeById(id);
        return R.ok(remove);
    }

    // 修改商品
    @PutMapping("/update")
    public R<Boolean> update(@RequestBody Product product) {
        boolean update = productService.updateById(product);
        return R.ok(update);
    }

    // 根据id查询
    @GetMapping("/get/{id}")
    public R<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return R.ok(product);
    }

    // 查询所有商品
    @GetMapping("/list")
    public R<List<Product>> list() {
        List<Product> list = productService.list();
        return R.ok(list);
    }
}