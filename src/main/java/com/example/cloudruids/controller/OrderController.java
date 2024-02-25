package com.example.cloudruids.controller;

import com.example.cloudruids.model.dto.OrderDto;
import com.example.cloudruids.model.requests.CheckoutRequest;
import com.example.cloudruids.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderDto> checkoutOrder(@RequestBody CheckoutRequest checkoutRequest) {
        OrderDto orderDto = orderService.checkoutOrder(checkoutRequest.getCartId());
        return ResponseEntity.ok(orderDto);
    }
}