package ru.practicum.mapper;

import ru.practicum.dto.category.CategoryRequestDto;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.entity.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryRequestDto dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
