package com.shobby.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Long id;
    private String sku;
    private String name;
    private String imageUrl;
    private String description;
    private BigDecimal sellingPrice;
    private Long categoryId;
    private boolean isEnabled;
}
