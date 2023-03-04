package ru.practicum.service.category;

import ru.practicum.entity.Category;

import java.util.Collection;

public interface CategoryService {

    Category create(Category category);

    void deleteById(Long catId);

    Category update(Long catId, Category category);

    Collection<Category> findCategories(Integer from, Integer size);

    Category findById(Long id);
}
