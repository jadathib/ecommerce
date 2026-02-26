package com.jada.ecommerce.controller;

import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    //CREATE PRODUCTSERVICE AS PRIVATE FIELD
    private final ProductService productService;

    //CONSTRUCTOR INJECT
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    //GETMAPPING
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
