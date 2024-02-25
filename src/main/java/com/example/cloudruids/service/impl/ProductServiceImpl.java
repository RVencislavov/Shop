package com.example.cloudruids.service.impl;

import com.example.cloudruids.mapper.ProductsMapper;
import com.example.cloudruids.model.dto.ProductsDto;
import com.example.cloudruids.repository.ProductsRepository;
import com.example.cloudruids.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;
    @Override
    public List<ProductsDto> getAllProducts() {
        return productsRepository.findAll().stream()
                .map(productsMapper::productsToProductsDto)
                .toList();
    }
}
