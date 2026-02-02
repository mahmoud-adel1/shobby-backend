package com.shobby.product.mapper;


import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductRequest;
import com.shobby.product.dto.ProductResponse;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.entity.Product;

public class ProductMapper {
    public static ProductCommand toCommand(ProductRequest productRequest) {
        return ProductCommand
                .builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .imageUrl(productRequest.getImageUrl())
                .description(productRequest.getDescription())
                .sellingPrice(productRequest.getSellingPrice())
                .categoryId(productRequest.getCategoryId())
                .build();
    }

    public static Product toEntity(ProductCommand productCommand) {
        return Product
                .builder()
                .sku(productCommand.getSku())
                .name(productCommand.getName())
                .imageUrl(productCommand.getImageUrl())
                .description(productCommand.getDescription())
                .sellingPrice(productCommand.getSellingPrice())
                .build();
    }

    public static ProductResult toResult(Product product) {
        return ProductResult
                .builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .categoryId(product.getCategory().getId())
                .isEnabled(product.isEnabled())
                .build();
    }

    public static ProductResponse toResponse(ProductResult productResult) {
        return ProductResponse
                .builder()
                .id(productResult.getId())
                .sku(productResult.getSku())
                .name(productResult.getName())
                .imageUrl(productResult.getImageUrl())
                .sellingPrice(productResult.getSellingPrice())
                .categoryId(productResult.getCategoryId())
                .isEnabled(productResult.isEnabled())
                .build();
    }

    public static void updateProduct(Product product, ProductCommand productCommand) {
        product.setSku(productCommand.getSku());
        product.setName(productCommand.getName());
        product.setImageUrl(productCommand.getImageUrl());
        product.setDescription(productCommand.getDescription());
        product.setSellingPrice(productCommand.getSellingPrice());
    }
}
