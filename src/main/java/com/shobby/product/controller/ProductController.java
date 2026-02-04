package com.shobby.product.controller;

import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductRequest;
import com.shobby.product.dto.ProductResponse;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getAllEnabledProducts(
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        return productService
                .getAllEnabledProducts(pageable)
                .map(ProductMapper::toResponse);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getEnabledProductById(@PathVariable long productId) {
        return ProductMapper.toResponse(productService.getEnabledProductById(productId));
    }

    @GetMapping("/sku/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getEnabledProductBySku(@PathVariable String sku) {
        ProductResult productResult = productService.getEnabledProductBySku(sku);
        return ProductMapper.toResponse(productResult);
    }

    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getEnabledProductsByCategoryId(
            @PathVariable long categoryId,
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable
    ) {
        return productService
                .getEnabledProductsByCategoryId(categoryId, pageable)
                .map(ProductMapper::toResponse);
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ProductResponse> getAllProducts(
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        return productService
                .getAllProducts(pageable)
                .map(ProductMapper::toResponse);
    }

    @GetMapping("/admin/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse getProductById(@PathVariable long productId) {
        return ProductMapper.toResponse(productService.getProductById(productId));
    }

    @GetMapping("/admin/sku/{sku}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse getProductBySku(@PathVariable String sku) {
        ProductResult productResult = productService.getProductBySku(sku);
        return ProductMapper.toResponse(productResult);
    }


    @GetMapping("/admin/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ProductResponse> getProductsByCategoryId(
            @PathVariable long categoryId,
            @PageableDefault(direction = Sort.Direction.ASC, sort = "id") Pageable pageable) {
        return productService
                .getProductsByCategoryId(categoryId, pageable)
                .map(ProductMapper::toResponse);
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
