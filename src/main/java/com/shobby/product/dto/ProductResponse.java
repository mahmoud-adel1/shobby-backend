package com.shobby.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponse {
    private String name;
    private long categoryId;
    private BigDecimal sellingPrice;
    private int stockQuantity;
    private boolean isEnabled;
}
