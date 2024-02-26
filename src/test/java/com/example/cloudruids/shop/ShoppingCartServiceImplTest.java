package com.example.cloudruids.shop;

import com.example.cloudruids.mapper.ShoppingCartItemsMapper;
import com.example.cloudruids.mapper.ShoppingCartMapper;
import com.example.cloudruids.model.dto.ProductQuantityDto;
import com.example.cloudruids.model.dto.ShoppingCartDto;
import com.example.cloudruids.model.entity.Products;
import com.example.cloudruids.model.entity.ShoppingCart;
import com.example.cloudruids.model.entity.ShoppingCartItems;
import com.example.cloudruids.model.enums.DealType;
import com.example.cloudruids.model.requests.AddProductsRequest;
import com.example.cloudruids.repository.ProductsRepository;
import com.example.cloudruids.repository.ShoppinCartRepository;
import com.example.cloudruids.repository.ShoppingCartItemsRepository;
import com.example.cloudruids.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


@SpringBootTest
class ShoppingCartServiceImplTest {

    @Mock
    private ShoppinCartRepository shoppingCartRepository;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private ShoppingCartItemsMapper shoppingCartItemsMapper;
    @Mock
    private ShoppingCartItemsRepository shoppingCartItemsRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void whenCalculatingTotalWithDeals_thenCorrectTotalIsReturned() {
        Long cartId = 1L;
        ShoppingCart mockCart = new ShoppingCart();
        mockCart.setItems(List.of(
                createShoppingCartItem(DealType.TWO_FOR_THREE, 1, 0.50),
                createShoppingCartItem(DealType.TWO_FOR_THREE, 3, 0.40),
                createShoppingCartItem(DealType.TWO_FOR_THREE, 1, 0.30),
                createShoppingCartItem(DealType.BUY_ONE_GET_ONE_HALF_PRICE, 2, 0.26)
        ));

        when(shoppingCartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));

        double expectedTotal = 1.99;

        double resultTotal = shoppingCartService.calculateTotalWithDeals(cartId);

        assertEquals(expectedTotal, resultTotal, 0.01);
    }

    private ShoppingCartItems createShoppingCartItem(DealType dealType, int quantity, double price) {
        ShoppingCartItems item = new ShoppingCartItems();
        item.setDealType(dealType);
        item.setQuantity(quantity);
        Products product = new Products();
        product.setPrice(price);
        item.setProduct(product);
        return item;
    }

    @Test
    void addProductsToCart_NewCart_Success() {

        AddProductsRequest addProductRequest = new AddProductsRequest();
        addProductRequest.setCartId(null);
        ProductQuantityDto productQuantityDto = new ProductQuantityDto();
        productQuantityDto.setProductId(1L);
        productQuantityDto.setQuantity(2);
        addProductRequest.setProducts(List.of(productQuantityDto));

        ShoppingCart newCart = new ShoppingCart();
        ShoppingCartDto cartDto = new ShoppingCartDto();
        Products product = new Products();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(0.5);
        product.setDeal(DealType.TWO_FOR_THREE);
        ShoppingCartItems newItem = new ShoppingCartItems();

        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(newCart);
        when(productsRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(shoppingCartItemsMapper.toEntity(any(ProductQuantityDto.class), any(ShoppingCart.class), any(Products.class))).thenReturn(newItem);
        when(shoppingCartMapper.shoppingCartToShoppingCartDto(any(ShoppingCart.class))).thenReturn(cartDto);
    }

    @Test
    void addProductsToCart_ProductNotFound_ThrowsRuntimeException() {
        AddProductsRequest addProductRequest = new AddProductsRequest();
        ProductQuantityDto productQuantityDto = new ProductQuantityDto();
        productQuantityDto.setProductId(5L);
        productQuantityDto.setQuantity(1);
        addProductRequest.setProducts(List.of(productQuantityDto));

        when(productsRepository.findById(5L)).thenThrow(new RuntimeException("Product not found"));

        assertThrows(RuntimeException.class, () -> shoppingCartService.addProductsToCart(addProductRequest));

        verify(shoppingCartRepository, never()).save(any(ShoppingCart.class));
        verify(shoppingCartItemsRepository, never()).save(any(ShoppingCartItems.class));
    }

}