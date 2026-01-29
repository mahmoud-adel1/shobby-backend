package com.shobby.product.controller;

import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductRequest;
import com.shobby.product.dto.ProductResponse;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponse> getAllProducts() {
        return productService
                .getAllProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @GetMapping("/enabled")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllEnabledProducts() {
        return productService
                .getAllEnabledProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse getProductById(@PathVariable long productId) {
        return ProductMapper.toResponse(productService.getProductById(productId));
    }

    @GetMapping("/enabled/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getEnabledProductById(@PathVariable long productId) {
        return ProductMapper.toResponse(productService.getEnabledProductById(productId));
    }

    @GetMapping("/sku/{sku}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse getProductBySku(@PathVariable String sku) {
        return ProductMapper.toResponse(productService.getProductBySku(sku));
    }

    @GetMapping("/enabled/sku/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getEnabledProductBySku(@PathVariable String sku) {
        return ProductMapper.toResponse(productService.getEnabledProductBySku(sku));
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponse> getProductsByCategoryId(@PathVariable long categoryId) {
        return productService
                .getProductsByCategoryId(categoryId)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @GetMapping("/enabled/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getEnabledProductsByCategoryId(@PathVariable long categoryId) {
        return productService
                .getEnabledProductsByCategoryId(categoryId)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse create(@RequestBody @Valid ProductRequest productRequest) {
        ProductCommand productCommand = ProductMapper.toCommand(productRequest);
        ProductResult productResult = productService.create(productCommand);
        return ProductMapper.toResponse(productResult);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse update(@PathVariable long productId, @RequestBody @Valid ProductRequest productRequest) {
        ProductCommand productCommand = ProductMapper.toCommand(productRequest);
        ProductResult productResult = productService.update(productId, productCommand);
        return ProductMapper.toResponse(productResult);
    }

    @PatchMapping("/{productId}/disable")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse disable(@PathVariable long productId) {
        ProductResult productResult = productService.disable(productId);
        return ProductMapper.toResponse(productResult);
    }

    @PatchMapping("/{productId}/enable")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse enable(@PathVariable long productId) {
        ProductResult productResult = productService.enable(productId);
        return ProductMapper.toResponse(productResult);
    }

}
