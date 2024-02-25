package com.example.cloudruids.service.impl;

import com.example.cloudruids.mapper.OrderMapper;
import com.example.cloudruids.model.dto.OrderDto;
import com.example.cloudruids.model.entity.Order;
import com.example.cloudruids.model.entity.ShoppingCart;
import com.example.cloudruids.repository.OrderRepository;
import com.example.cloudruids.repository.ShoppinCartRepository;
import com.example.cloudruids.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppinCartRepository shoppinCartRepository;

    public OrderDto checkoutOrder(Long cartId) {
        double totalPrice = shoppingCartService.calculateTotalWithDeals(cartId);
        ShoppingCart cart = shoppinCartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        orderMapper.updateOrderFromCartAndPrice(order, cart, totalPrice);
        order = orderRepository.save(order);

        return orderMapper.orderToOrderDto(order);
    }
}
