package com.example.cloudruids.controller;


import com.example.cloudruids.model.dto.ShoppingCartDto;
import com.example.cloudruids.model.requests.AddProductsRequest;
import com.example.cloudruids.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add-products")
    public ResponseEntity<ShoppingCartDto> addProductsToCart(@RequestBody AddProductsRequest addProductRequest) {
        ShoppingCartDto shoppingCartDto = shoppingCartService.addProductsToCart(addProductRequest);
        return ResponseEntity.ok(shoppingCartDto);
    }


}
