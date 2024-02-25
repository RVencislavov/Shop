package com.example.cloudruids.repository;

import com.example.cloudruids.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppinCartRepository extends JpaRepository<ShoppingCart , Long> {
}
