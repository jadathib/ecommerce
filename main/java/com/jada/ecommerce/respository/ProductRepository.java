package com.jada.ecommerce.respository;

import com.jada.ecommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    //NEW LIST OF PRODUCTS
    private final List<Product> products = new ArrayList<>();

    //CONSTRUCTOR TO ADD PRODUCTS
    public ProductRepository() {
        products.add(new Product(1L, "Laptop", 1200.00, 10));
        products.add(new Product(2L, "Phone", 800.00, 15));
    }

    //RETURN THE LIST OF THE PRODUCTS
    public List<Product> findAll() {
        return products;
    }
}
