package ru.practicum.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.entity.Category;
import ru.practicum.exception.NotFoundException;
import ru.practicum.repository.CategoryRepository;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(long catId) {
        findById(catId);
        categoryRepository.deleteById(catId);
    }

    @Override
    public Category update(long catId, Category category) {
        category.setId(catId);
        return categoryRepository.save(category);
    }

    @Override
    public Collection<Category> findCategories(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size))
                .toList();
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id=" + id));
    }
}
