package com.example.cloudruids.model.dto;

import com.example.cloudruids.model.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemsDto {
        private Long id;
        private ProductsDto product;
        private Integer quantity;
        private DealType dealType;
}
