package com.jada.ecommerce.service;

import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.respository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    //CREATE NEW LIST OF THE PRODUCTS
    private List<Product> products = new ArrayList<>();

    //CONSTRUCTOR INJECT REPOSITORY INTO SERVICE
    private final ProductRepository productRepository;

    //CONSTRUCTOR TO INJECT
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //RETURN LIST
    public List<Product> getAllProducts (){
        //RETURN IT WITH THE REPOSITORY METHOD
        return productRepository.findAll();
    }
}
