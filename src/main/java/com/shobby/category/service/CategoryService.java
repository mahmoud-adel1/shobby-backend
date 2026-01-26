 package com.shobby.category.service;

import com.shobby.category.dto.CategoryCommand;
import com.shobby.category.dto.CategoryResult;
import com.shobby.category.entity.Category;
import com.shobby.category.exception.CategoryAlreadyDisabledException;
import com.shobby.category.exception.CategoryAlreadyExistsException;
import com.shobby.category.exception.CategoryNotExistsException;
import com.shobby.category.mapper.CategoryMapper;
import com.shobby.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResult> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResult)
                .toList();
    }

    public List<CategoryResult> getAllEnabledCategories() {
        return categoryRepository.findAllEnabledCategories()
                .stream()
                .map(CategoryMapper::toResult)
                .toList();
    }

    public CategoryResult getCategoryById(long categoryId) {
        Category category = getCategoryOrThrow(categoryId);
        return CategoryMapper.toResult(category);
    }

    @Transactional
    public CategoryResult create(CategoryCommand categoryCommand) {
        if (categoryExists(categoryCommand.getName())) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = CategoryMapper.toEntity(categoryCommand);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toResult(savedCategory);
    }


    public CategoryResult update(long categoryId, CategoryCommand categoryCommand) {
        Category category = getCategoryOrThrow(categoryId);
        CategoryMapper.updateCategory(category, categoryCommand);
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toResult(updatedCategory);
    }


    private boolean categoryExists(String categoryName) {
        return categoryRepository.existsByNameIgnoreCase(categoryName.trim());
    }

    public Category getCategoryOrThrow(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotExistsException::new);
    }

    public CategoryResult disable(long categoryId) {
        Category category = getCategoryOrThrow(categoryId);
        if (!category.isEnabled()) {
            throw new CategoryAlreadyDisabledException();
        }
        category.setEnabled(false);
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.toResult(updatedCategory);
    }
}
