package com.jada.ecommerce.controller;

import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.LongSummaryStatistics;

@RestController

@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    //CONSTRUCTER INJECTION
    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //POST MAPPING
    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody Category category) {
        //CREATE INSTANCE
        Category savedCategory = categoryService.createCategory(category);

        //BUILD THE LOCATION OF WHERE THE CATEGORY WAS SAVED
        URI location = URI.create("/categories/" + savedCategory.getId());

        //STATUS HEADER AND BODY
        return ResponseEntity.created(location).body(savedCategory);
    }

    //FETCH ALL CATEGORIES
    @GetMapping
    public List<Category> getAllCategories () {
        return categoryService.getAllCategories();
    }

    //GET CATEGORIES BY ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestBody Category updated) {

        Category existing = categoryService.getCategoryById(id);
        existing.setName(updated.getName());

        return categoryService.createCategory(existing);
    }


}
