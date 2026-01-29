package com.shobby.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommand {
    private String sku;
    private String name;
    private String description;
    private Long categoryId;
    private BigDecimal sellingPrice;
    private BigDecimal costPrice;
}
