package com.zunza.pick.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zunza.pick.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
