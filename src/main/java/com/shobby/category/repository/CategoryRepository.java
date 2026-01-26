package com.shobby.category.repository;

import com.shobby.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE isEnabled is true")
    List<Category> findAllEnabledCategories();
    boolean existsByNameIgnoreCase(String name);
}
