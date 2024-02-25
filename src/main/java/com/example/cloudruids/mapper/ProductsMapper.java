package com.example.cloudruids.mapper;

import com.example.cloudruids.model.dto.ProductsDto;
import com.example.cloudruids.model.entity.Products;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    ProductsDto productsToProductsDto(Products products);

    Products productsDtoToProducts(ProductsDto productsDto);
}
