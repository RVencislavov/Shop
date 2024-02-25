package com.example.cloudruids.repository;

import com.example.cloudruids.model.entity.ShoppingCartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItems ,Long> {
}
