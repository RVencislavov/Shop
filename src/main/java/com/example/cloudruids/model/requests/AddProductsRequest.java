package com.example.cloudruids.model.requests;

import com.example.cloudruids.model.dto.ProductQuantityDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class AddProductsRequest {
    private Long cartId;
    private List<ProductQuantityDto> products;
}
