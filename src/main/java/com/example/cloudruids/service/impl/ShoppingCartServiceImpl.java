package com.example.cloudruids.service.impl;

import com.example.cloudruids.mapper.ShoppingCartItemsMapper;
import com.example.cloudruids.mapper.ShoppingCartMapper;
import com.example.cloudruids.model.dto.ShoppingCartDto;
import com.example.cloudruids.model.entity.Products;
import com.example.cloudruids.model.entity.ShoppingCart;
import com.example.cloudruids.model.entity.ShoppingCartItems;
import com.example.cloudruids.model.enums.DealType;
import com.example.cloudruids.model.requests.AddProductsRequest;
import com.example.cloudruids.repository.ProductsRepository;
import com.example.cloudruids.repository.ShoppinCartRepository;
import com.example.cloudruids.repository.ShoppingCartItemsRepository;
import com.example.cloudruids.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppinCartRepository shoppingCartRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ShoppingCartItemsRepository shoppingCartItemsRepository;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private ShoppingCartItemsMapper shoppingCartItemsMapper;
    @Transactional
    @Override
    public ShoppingCartDto addProductsToCart(AddProductsRequest addProductRequest) {
        ShoppingCart cart = Optional.ofNullable(addProductRequest.getCartId())
                .flatMap(shoppingCartRepository::findById)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    return shoppingCartRepository.save(newCart);
                });

        addProductRequest.getProducts().forEach(productQuantityDto -> {
            Products product = productsRepository.findById(productQuantityDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            ShoppingCartItems newItem = shoppingCartItemsMapper.toEntity(productQuantityDto, cart, product);
            newItem.setDealType(product.getDeal());
            shoppingCartItemsRepository.save(newItem);
        });

        return shoppingCartMapper.shoppingCartToShoppingCartDto(cart);
    }

    @Override
    public double calculateTotalWithDeals(Long cartId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        double totalWithoutDiscounts = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        double discount = calculateDiscounts(cart.getItems());

        return totalWithoutDiscounts - discount;
    }

    private double calculateDiscounts(List<ShoppingCartItems> items) {
        double discount = 0;

        List<ShoppingCartItems> sortedTwoForThreeItems = items.stream()
                .filter(item -> item.getDealType() == DealType.TWO_FOR_THREE)
                .sorted(Comparator.comparingDouble(item -> item.getProduct().getPrice()))
                .collect(Collectors.toList());

        int itemsProcessedForTwoForThree = 0;
        for (ShoppingCartItems item : sortedTwoForThreeItems) {
            int eligibleForDiscount = item.getQuantity() / 3;
            discount += eligibleForDiscount * item.getProduct().getPrice();
            itemsProcessedForTwoForThree += eligibleForDiscount * 3;
        }

        List<ShoppingCartItems> halfPriceItems = items.stream()
                .filter(item -> item.getDealType() == DealType.BUY_ONE_GET_ONE_HALF_PRICE)
                .collect(Collectors.toList());

        for (ShoppingCartItems item : halfPriceItems) {
            discount += (item.getQuantity() / 2) * (item.getProduct().getPrice() / 2);
        }

        return discount;
    }



}
