package com.example.cloudruids.mapper;

import com.example.cloudruids.model.dto.ShoppingCartDto;
import com.example.cloudruids.model.entity.ShoppingCart;
import com.example.cloudruids.model.entity.ShoppingCartItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShoppingCartItemsMapper.class})
public interface ShoppingCartMapper {

    @Mapping(source = "items", target = "items")
    ShoppingCartDto shoppingCartToShoppingCartDto(ShoppingCart shoppingCart);

    @Mapping(source = "items", target = "items")
    ShoppingCart shoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto);
}
