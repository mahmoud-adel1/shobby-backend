package com.shobby.product.service;

import com.shobby.category.entity.Category;
import com.shobby.category.service.CategoryService;
import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.entity.Product;
import com.shobby.product.exception.EnabledProductNotFoundException;
import com.shobby.product.exception.ProductNotFoundException;
import com.shobby.product.exception.ProductSkuAlreadyExistsException;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public ProductResult getProductById(long productId) {
        Product product = getProductOrThrow(productId);
        return ProductMapper.toResult(product);
    }

    public ProductResult getEnabledProductById(long productId) {
        Product product = productRepository.findById(productId)
                .filter(Product::isEnabled)
                .orElseThrow(EnabledProductNotFoundException::new);
        return ProductMapper.toResult(product);
    }

    public ProductResult getProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(ProductNotFoundException::new);
        return ProductMapper.toResult(product);
    }

    public ProductResult getEnabledProductBySku(String sku) {
        Product product = productRepository.findBySku(sku)
                .filter(Product::isEnabled)
                .orElseThrow(EnabledProductNotFoundException::new);
        return ProductMapper.toResult(product);
    }

    public List<ProductResult> getProductsByCategoryId(long categoryId) {
        return productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductMapper::toResult)
                .toList();
    }

    public List<ProductResult> getEnabledProductsByCategoryId(long categoryId) {
        return productRepository
                .findByCategoryId(categoryId)
                .stream()
                .filter(Product::isEnabled)
                .map(ProductMapper::toResult)
                .toList();
    }

    public ProductResult create(ProductCommand productCommand) {
        if (productRepository.existsBySku(productCommand.getSku())) {
            throw new ProductSkuAlreadyExistsException();
        }
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = ProductMapper.toEntity(productCommand);
        product.setCategory(category);
        product.setEnabled(true);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResult(savedProduct);
    }

    public ProductResult update(long productId, ProductCommand productCommand) {
        Category category = categoryService.getCategoryOrThrow(productCommand.getCategoryId());
        Product product = getProductOrThrow(productId);

        // Check Sku is duplicated or not
        Optional<Product> productBySku = productRepository.findBySku(productCommand.getSku());
        if (productBySku.isPresent()) {
            if (!Objects.equals(productBySku.get().getId(), product.getId())) {
                throw new ProductSkuAlreadyExistsException();
            }
        }

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

    public ProductResult enable(long productId) {
        Product product = getProductOrThrow(productId);
        product.setEnabled(true);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResult(updatedProduct);
    }

    private Product getProductOrThrow(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

}
