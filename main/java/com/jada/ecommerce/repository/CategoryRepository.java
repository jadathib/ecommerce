package com.jada.ecommerce.repository;

import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    Optional<Category> findByNameIgnoreCase(String name);
}
