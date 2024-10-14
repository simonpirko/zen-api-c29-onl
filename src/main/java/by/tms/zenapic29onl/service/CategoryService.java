package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Category;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CrudOperation<Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Category update(Category category) {
        Category existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setImage(category.getImage());
        return categoryRepository.save(existingCategory);

    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
