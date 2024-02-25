package com.example.cloudruids.model.dto;

import com.example.cloudruids.model.enums.DealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
        private Long id;
        private String name;
        private Double price;
        private DealType deal;
}
