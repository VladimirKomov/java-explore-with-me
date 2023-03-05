package ru.practicum.mapper;

import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.entity.Category;

import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category toCategory(NewCategoryDto dto) {
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

    public static Collection<CategoryResponseDto> toCategoryResponseDtoCollection(Collection<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toCategoryResponseDto)
                .collect(Collectors.toList());
    }
}
