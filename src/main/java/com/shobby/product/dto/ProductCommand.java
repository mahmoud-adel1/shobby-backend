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
    private String imageUrl;
    private String description;
    private BigDecimal sellingPrice;
    private Long categoryId;
}
