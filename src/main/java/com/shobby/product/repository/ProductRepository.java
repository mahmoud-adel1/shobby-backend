package com.shobby.product.repository;

import com.shobby.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByEnabledTrue(Pageable pageable);
    Page<Product> findByCategoryId(long categoryId, Pageable pageable);
    Page<Product> findByCategoryIdAndEnabledTrue(long categoryId, Pageable pageable);
    Optional<Product> findBySku(String sku);
}
