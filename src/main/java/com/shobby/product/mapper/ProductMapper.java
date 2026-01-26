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
                .name(productRequest.getName())
                .categoryId(productRequest.getCategoryId())
                .sellingPrice(productRequest.getSellingPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .build();
    }

    public static Product toEntity(ProductCommand productCommand) {
        return Product
                .builder()
                .name(productCommand.getName())
                .sellingPrice(productCommand.getSellingPrice())
                .stockQuantity(productCommand.getStockQuantity())
                .build();
    }

    public static ProductResult toResult(Product product) {
        return ProductResult
                .builder()
                .name(product.getName())
                .categoryId(product.getCategory().getId())
                .sellingPrice(product.getSellingPrice())
                .stockQuantity(product.getStockQuantity())
                .isEnabled(product.isEnabled())
                .build();
    }

    public static ProductResponse toResponse(ProductResult productResult) {
        return ProductResponse
                .builder()
                .name(productResult.getName())
                .categoryId(productResult.getCategoryId())
                .sellingPrice(productResult.getSellingPrice())
                .stockQuantity(productResult.getStockQuantity())
                .isEnabled(productResult.isEnabled())
                .build();
    }

    public static void updateProduct(Product product, ProductCommand productCommand) {
        product.setName(productCommand.getName());
        product.setSellingPrice(productCommand.getSellingPrice());
        product.setStockQuantity(productCommand.getStockQuantity());
        product.setEnabled(true);
    }
}
