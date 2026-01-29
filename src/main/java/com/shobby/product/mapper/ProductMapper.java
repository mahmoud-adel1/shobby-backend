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
                .description(productRequest.getDescription())
                .categoryId(productRequest.getCategoryId())
                .sellingPrice(productRequest.getSellingPrice())
                .costPrice(productRequest.getCostPrice())
                .build();
    }

    public static Product toEntity(ProductCommand productCommand) {
        return Product
                .builder()
                .sku(productCommand.getSku())
                .name(productCommand.getName())
                .description(productCommand.getDescription())
                .sellingPrice(productCommand.getSellingPrice())
                .costPrice(productCommand.getCostPrice())
                .build();
    }

    public static ProductResult toResult(Product product) {
        return ProductResult
                .builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .sellingPrice(product.getSellingPrice())
                .costPrice(product.getCostPrice())
                .isEnabled(product.isEnabled())
                .build();
    }

    public static ProductResponse toResponse(ProductResult productResult) {
        return ProductResponse
                .builder()
                .id(productResult.getId())
                .sku(productResult.getSku())
                .name(productResult.getName())
                .categoryId(productResult.getCategoryId())
                .sellingPrice(productResult.getSellingPrice())
                .costPrice(productResult.getCostPrice())
                .isEnabled(productResult.isEnabled())
                .build();
    }

    public static void updateProduct(Product product, ProductCommand productCommand) {
        product.setSku(productCommand.getSku());
        product.setName(productCommand.getName());
        product.setDescription(productCommand.getDescription());
        product.setSellingPrice(productCommand.getSellingPrice());
        product.setCostPrice(productCommand.getCostPrice());
    }
}
