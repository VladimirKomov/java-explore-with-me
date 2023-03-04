package ru.practicum.controller.publ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.service.category.CategoryService;

import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoryController {

    private final CategoryService categoryService;


    /**
     * Получение категорий
     */
    @GetMapping
    public Collection<CategoryResponseDto> findCategories(
            @RequestParam(defaultValue = "0") @Min(0) Integer from,
            @RequestParam(defaultValue = "10") @Min(1) Integer size) {
        log.info("GET categories by from={}, size={}", from, size);
        return CategoryMapper.toCategoryResponseDtoCollection(
                categoryService.findCategories(from, size));
    }

    /**
     * Получение информации о категории по её идентификатору
     */
    @GetMapping("{catId}")
    public CategoryResponseDto findCategories(
            @PathVariable @Min(1) Long catId) {
        log.info("GET category by id={}, size={}", catId);
        return CategoryMapper.toCategoryResponseDto(
                categoryService.findById(catId));
    }
}
