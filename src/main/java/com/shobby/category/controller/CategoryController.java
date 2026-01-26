package com.shobby.category.controller;

import com.shobby.category.dto.CategoryCommand;
import com.shobby.category.dto.CategoryRequest;
import com.shobby.category.dto.CategoryResponse;
import com.shobby.category.dto.CategoryResult;
import com.shobby.category.mapper.CategoryMapper;
import com.shobby.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResult> categoryResults = categoryService.getAllCategories();
        return categoryResults
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @GetMapping("/enabled")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllEnabledCategories() {
        List<CategoryResult> categoryResults = categoryService.getAllEnabledCategories();
        return categoryResults
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable long categoryId) {
        CategoryResult categoryResult = categoryService.getCategoryById(categoryId);
        return CategoryMapper.toResponse(categoryResult);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryCommand categoryCommand = CategoryMapper.toCommand(categoryRequest);
        CategoryResult categoryResult = categoryService.create(categoryCommand);
        return CategoryMapper.toResponse(categoryResult);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse update(@PathVariable long categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryCommand categoryCommand = CategoryMapper.toCommand(categoryRequest);
        CategoryResult categoryResult = categoryService.update(categoryId, categoryCommand);
        return CategoryMapper.toResponse(categoryResult);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse disable(@PathVariable long categoryId) {
        CategoryResult categoryResult = categoryService.disable(categoryId);
        return CategoryMapper.toResponse(categoryResult);
    }

}
