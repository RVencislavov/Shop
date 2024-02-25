package com.example.cloudruids.controller;

import com.example.cloudruids.model.dto.ProductsDto;
import com.example.cloudruids.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<ProductsDto>> getAllProducts() {
        List<ProductsDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}