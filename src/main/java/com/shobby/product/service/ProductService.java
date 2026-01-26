package com.shobby.product.service;

import com.shobby.category.entity.Category;
import com.shobby.category.service.CategoryService;
import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.entity.Product;
import com.shobby.product.exception.ProductNotFoundException;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductResult> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductMapper::toResult)
                .toList();
    }

    public List<ProductResult> getAllEnabledProducts() {
        return productRepository
                .findAllEnabledProducts()
                .stream()
                .map(ProductMapper::toResult)
                .toList();
    }



    public ProductResult create(ProductCommand productCommand) {
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = ProductMapper.toEntity(productCommand);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResult(savedProduct);
    }

    public ProductResult update(long productId, ProductCommand productCommand) {
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = getProductOrThrow(productId);
        ProductMapper.updateProduct(product, productCommand);
        product.setCategory(category);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    public ProductResult disable(long productId) {
        Product product = getProductOrThrow(productId);
        product.setEnabled(false);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    private Product getProductOrThrow(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

}
