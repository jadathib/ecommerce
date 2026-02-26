package com.jada.ecommerce.service;

import com.jada.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    //CREATE NEW LIST OF THE PRODUCTS
    private List<Product> products = new ArrayList<>();

    //ADD ITEMS
    public ProductService() {
        products.add(new Product(1L, "Laptop", 1200.00, 10));
        products.add(new Product(2L, "Phone", 800.00, 15));
    }

    //RETURN LIST
    public List<Product> getAllProducts (){
        return products;
    }
}
