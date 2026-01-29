package com.shobby.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResult {
    private Long id;
    private String sku;
    private String name;
    private String description;
    private long categoryId;
    private BigDecimal sellingPrice;
    private BigDecimal costPrice;
    private boolean isEnabled;
}
