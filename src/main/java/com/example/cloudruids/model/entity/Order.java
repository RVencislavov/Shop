package com.example.cloudruids.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private ShoppingCart cart;
}