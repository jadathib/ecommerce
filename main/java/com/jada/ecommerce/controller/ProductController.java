package com.jada.ecommerce.controller;

import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //CREATE PRODUCTSERVICE AS PRIVATE FIELD
    private final ProductService productService;

    //CONSTRUCTOR INJECT
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    //GETMAPPING
    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    //POSTMAPPING
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

}
