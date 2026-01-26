package com.shobby.product.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommand {
    private String name;
    private long categoryId;
    private BigDecimal sellingPrice;
    private int stockQuantity;

}
