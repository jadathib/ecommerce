package com.jada.ecommerce.service;

import com.jada.ecommerce.exception.ResourceNotFoundException;
import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    //CONSTRUCTOR INJECTION
    public CategoryService (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //GET ALL CATEGORIES
    public List<Category> getAllCategories () {
        return categoryRepository.findAll();
    }

    //CREATE CATEGORY
    public Category createCategory (Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    //DELETE CATEGORY
    public void deleteCategory (Long id) {
        //FIND OBJECT IN DATABASE AND ASSIGN TO CATEGORY
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id));
        //DELETE IT
        categoryRepository.delete(category);
    }
}
