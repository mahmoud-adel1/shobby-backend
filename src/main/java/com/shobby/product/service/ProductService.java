package com.shobby.product.service;

import com.shobby.category.entity.Category;
import com.shobby.category.service.CategoryService;
import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.entity.Product;
import com.shobby.product.exception.ProductDisabledException;
import com.shobby.product.exception.ProductNotFoundException;
import com.shobby.product.exception.ProductSkuAlreadyExistsException;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<ProductResult> getAllEnabledProducts(Pageable pageable) {
        return productRepository
                .findByEnabledTrue(pageable)
                .map(ProductMapper::toResult);
    }

    public Page<ProductResult> getEnabledProductsByCategoryId(long categoryId, Pageable pageable) {
        return productRepository
                .findByCategoryIdAndEnabledTrue(categoryId, pageable)
                .map(ProductMapper::toResult);
    }

    public ProductResult getEnabledProductById(long productId) {
        Product product = getProductByIdOrThrow(productId);
        if (!product.isEnabled()) {
            throw new ProductDisabledException();
        }
        return ProductMapper.toResult(product);
    }

    public ProductResult getEnabledProductBySku(String sku) {
        Product product = getProductBySkuOrThrow(sku);
        if (!product.isEnabled()) {
            throw new ProductDisabledException();
        }
        return ProductMapper.toResult(product);
    }

    public Page<ProductResult> getAllProducts(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(ProductMapper::toResult);
    }

    public Page<ProductResult> getProductsByCategoryId(long categoryId, Pageable pageable) {
        return productRepository
                .findByCategoryId(categoryId, pageable)
                .map(ProductMapper::toResult);
    }

    public ProductResult getProductById(long productId) {
        Product product = getProductByIdOrThrow(productId);
        return ProductMapper.toResult(product);
    }

    public ProductResult getProductBySku(String sku) {
        Product product = getProductBySkuOrThrow(sku);
        return ProductMapper.toResult(product);
    }

    public ProductResult create(ProductCommand productCommand) {
        productRepository.findBySku(productCommand.getSku())
                .ifPresent(product -> {throw new ProductSkuAlreadyExistsException();});
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = ProductMapper.toEntity(productCommand);
        product.setCategory(category);
        product.setEnabled(true);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResult(savedProduct);
    }

    public ProductResult update(long productId, ProductCommand productCommand) {
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = getProductByIdOrThrow(productId);

        // Check Sku is duplicated or not
        productRepository.findBySku(productCommand.getSku())
                        .filter(product1 -> !product.getId().equals(product1.getId()))
                        .ifPresent(product1 -> {throw new ProductSkuAlreadyExistsException();});

        ProductMapper.updateProduct(product, productCommand);
        product.setCategory(category);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    public ProductResult disable(long productId) {
        Product product = getProductByIdOrThrow(productId);
        product.setEnabled(false);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    public ProductResult enable(long productId) {
        Product product = getProductByIdOrThrow(productId);
        product.setEnabled(true);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    private Product getProductByIdOrThrow(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    private Product getProductBySkuOrThrow(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(ProductNotFoundException::new);
    }

    public boolean isProductAvailable(Long productId) {
        return productRepository.existsById(productId);
    }

}
