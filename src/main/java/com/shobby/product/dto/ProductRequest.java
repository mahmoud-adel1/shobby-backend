package com.shobby.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "PRODUCT_SKU_REQUIRED")
    private String sku;

    @NotBlank(message = "PRODUCT_NAME_REQUIRED")
    private String name;

    @NotNull(message = "CATEGORY_ID_REQUIRED")
    private Long categoryId;

    private String description;

    @NotNull(message = "COST_PRICE_REQUIRED")
    @DecimalMin(value = "0.01", message = "SELLING_PRICE_POSITIVE_REQUIRED")
    private BigDecimal sellingPrice;

    @NotNull(message = "COST_PRICE_REQUIRED")
    @DecimalMin(value = "0.01", message = "COST_PRICE_POSITIVE_REQUIRED")
    private BigDecimal costPrice;
}
