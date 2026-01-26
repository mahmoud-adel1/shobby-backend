package com.shobby.product.controller;

import com.shobby.product.dto.ProductCommand;
import com.shobby.product.dto.ProductRequest;
import com.shobby.product.dto.ProductResponse;
import com.shobby.product.dto.ProductResult;
import com.shobby.product.mapper.ProductMapper;
import com.shobby.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService
                .getAllProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @GetMapping("/enabled")
    public List<ProductResponse> getAllEnabledProducts() {
        return productService
                .getAllEnabledProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        ProductCommand productCommand = ProductMapper.toCommand(productRequest);
        ProductResult productResult = productService.create(productCommand);
        return ProductMapper.toResponse(productResult);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable long productId, @RequestBody ProductRequest productRequest) {
        ProductCommand productCommand = ProductMapper.toCommand(productRequest);
        ProductResult productResult = productService.update(productId, productCommand);
        return ProductMapper.toResponse(productResult);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse disable(@PathVariable long productId) {
        ProductResult productResult = productService.disable(productId);
        return ProductMapper.toResponse(productResult);
    }


}
