package ru.practicum.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.entity.Category;
import ru.practicum.exception.NotFoundException;
import ru.practicum.repository.CategoryRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long catId) {
        categoryRepository.deleteById(catId);
    }

    @Override
    public Category update(Long catId, Category category) {
        category.setId(catId);
        return categoryRepository.save(category);
    }

    @Override
    public Collection<Category> findCategories(Integer from, Integer size) {
        return categoryRepository.findAll(PageRequest.of(from, size))
                .toList();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id=" + id));
    }
}
