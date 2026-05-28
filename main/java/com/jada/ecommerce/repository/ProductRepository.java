package com.jada.ecommerce.repository;

import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//ORM BASED SQL COMMANDS
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByPrice(double price);

    public List<Product> findByNameContainingIgnoreCase(String name);

    public List<Product> findByCategory (Category category);
}
