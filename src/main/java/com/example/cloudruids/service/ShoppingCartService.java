package com.example.cloudruids.service;

import com.example.cloudruids.model.dto.ShoppingCartDto;
import com.example.cloudruids.model.requests.AddProductsRequest;

public interface ShoppingCartService {
    ShoppingCartDto addProductsToCart(AddProductsRequest addProductRequest);

    double calculateTotalWithDeals(Long cartId);
}
