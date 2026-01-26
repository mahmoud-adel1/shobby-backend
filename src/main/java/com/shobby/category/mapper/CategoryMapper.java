package com.shobby.category.mapper;

import com.shobby.category.dto.CategoryCommand;
import com.shobby.category.dto.CategoryRequest;
import com.shobby.category.dto.CategoryResponse;
import com.shobby.category.dto.CategoryResult;
import com.shobby.category.entity.Category;

public class CategoryMapper {

    public static CategoryCommand toCommand(CategoryRequest categoryRequest) {
        return CategoryCommand
                .builder()
                .name(categoryRequest.getName())
                .build();
    }

    public static Category toEntity(CategoryCommand categoryCommand) {
        return Category
                .builder()
                .name(categoryCommand.getName())
                .build();
    }

    public static CategoryResult toResult(Category category) {
        return CategoryResult
                .builder()
                .name(category.getName())
                .isEnabled(category.isEnabled())
                .build();
    }

    public static CategoryResponse toResponse(CategoryResult categoryResult) {
        return CategoryResponse
                .builder()
                .name(categoryResult.getName())
                .isEnabled(categoryResult.isEnabled())
                .build();
    }


    public static void updateCategory(Category category, CategoryCommand categoryCommand) {
        category.setName(categoryCommand.getName());
    }
}
