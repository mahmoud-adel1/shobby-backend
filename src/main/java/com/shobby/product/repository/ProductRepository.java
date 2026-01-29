package com.shobby.product.repository;

import com.shobby.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Products p WHERE p.isEnabled is true")
    List<Product> findAllEnabledProducts();
    Optional<Product> findBySku(String sku);
    Optional<Product> findById(long id);
    List<Product> findByCategoryId(long categoryId);
    boolean existsBySku(String sku);
}
