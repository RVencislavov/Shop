package com.example.cloudruids.mapper;

import com.example.cloudruids.model.dto.ProductQuantityDto;
import com.example.cloudruids.model.dto.ShoppingCartItemsDto;
import com.example.cloudruids.model.entity.Products;
import com.example.cloudruids.model.entity.ShoppingCart;
import com.example.cloudruids.model.entity.ShoppingCartItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductsMapper.class})
public interface ShoppingCartItemsMapper {

    @Mapping(source = "product", target = "product")
    ShoppingCartItemsDto shoppingCartItemsToShoppingCartItemsDto(ShoppingCartItems shoppingCartItems);

    @Mapping(source = "product", target = "product")
    ShoppingCartItems shoppingCartItemsDtoToShoppingCartItems(ShoppingCartItemsDto shoppingCartItemsDto);
    @Mapping(target = "id", ignore = true)
    ShoppingCartItems toEntity(ProductQuantityDto productQuantityDto, ShoppingCart cart, Products product);
}