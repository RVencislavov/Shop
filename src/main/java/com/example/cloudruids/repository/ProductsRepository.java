package com.example.cloudruids.repository;

import com.example.cloudruids.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
