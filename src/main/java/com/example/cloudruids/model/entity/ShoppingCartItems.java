package com.example.cloudruids.model.entity;

import com.example.cloudruids.model.enums.DealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shoppingCartItems")
public class ShoppingCartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private DealType dealType;

    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Products product;

}
