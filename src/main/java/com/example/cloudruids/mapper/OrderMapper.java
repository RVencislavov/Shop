package com.example.cloudruids.mapper;


import com.example.cloudruids.model.dto.OrderDto;
import com.example.cloudruids.model.entity.Order;
import com.example.cloudruids.model.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ShoppingCartMapper.class})
public interface OrderMapper {

    @Mapping(source = "cart", target = "cart")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "cart", target = "cart")
    Order orderDtoToOrder(OrderDto orderDto);
    @Mapping(target = "id", ignore = true)
    void updateOrderFromCartAndPrice(@MappingTarget Order order, ShoppingCart cart, Double totalPrice);
}

