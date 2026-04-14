package com.jada.ecommerce.controller;

import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//HANDLES HTTP REQUESTS AND RETURNS JSON
@RestController
//BASE URL PATH
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //INJECTS PRODUCT SERVICE
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CREATING A PRODUCT
    @PostMapping
    //USE REQUEST BODY BECAUSE THAT IS WHERE THE DATA IS GOING IN THE REQUEST
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);

        //BUILD THE LOCATION OF WHERE THE PRODUCT WAS SAVED
        URI location = URI.create("/products/" + savedProduct.getId());

        //STATUS, HEADER, AND BODY
        return ResponseEntity.created(location).body(savedProduct);
    }

    //FETCHING ALL PRODUCTS
    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    //GET A PRODUCT BY ID
    @GetMapping("/{id}")
    //RESPONSEENTITY<PRODUCT>
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        //CREATE INSTANCE WITH PRODUCTSERVICE
        Product product = productService.getProductById(id);
        //IF PRODUCT EXISTS
        if (product != null) {
            //RETURN RESPONSEENTITY.OK
            return ResponseEntity.ok(product);
        } else {
            //ELSE NOTFOUND
            return ResponseEntity.notFound().build();
        }
    }
}
