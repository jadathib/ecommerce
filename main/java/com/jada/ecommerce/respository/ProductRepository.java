package com.jada.ecommerce.respository;

import com.jada.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//ORM BASED SQL COMMANDS
public interface ProductRepository extends JpaRepository<Product, Long> {

}
