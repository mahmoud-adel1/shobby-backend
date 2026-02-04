package com.shobby.product.dto;

import com.shobby.category.dto.CategoryResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductResult {
    private Long id;
    private String sku;
    private String name;
    private String imageUrl;
    private String description;
    private BigDecimal sellingPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;
    private CategoryResult categoryResult;
}
