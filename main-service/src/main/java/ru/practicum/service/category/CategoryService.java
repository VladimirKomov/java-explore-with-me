package ru.practicum.service.category;

import ru.practicum.entity.Category;

public interface CategoryService {

    Category create(Category category);
    void deleteById(Long catId);
    Category update(Category category);
}
