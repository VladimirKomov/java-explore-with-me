package ru.practicum.service.category;

import ru.practicum.entity.Category;

import java.util.Collection;

public interface CategoryService {

    Category create(Category category);

    void deleteById(long catId);

    Category update(long catId, Category category);

    Collection<Category> findCategories(int from, int size);

    Category findById(long id);
}
