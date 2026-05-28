package com.jada.ecommerce.controller;

import com.jada.ecommerce.config.AppConfig;
import com.jada.ecommerce.exception.ResourceNotFoundException;
import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
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
    private final AppConfig appConfig;

    //INJECTS PRODUCT SERVICE
    public ProductController(ProductService productService, AppConfig appConfig) {
        this.productService = productService;
        this.appConfig = appConfig;
    }

    //CREATING A PRODUCT
    @PostMapping
    //USE REQUEST BODY BECAUSE THAT IS WHERE THE DATA IS GOING IN THE REQUEST
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        //CREATE INSTANCE
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
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    //GET A PRODUCT BY CATEGORY ID
    @GetMapping("/category/{id}")
    public List<Product> getProductByCategoryId (@PathVariable Long id) {
        return productService.getProductByCategoryId(id);
    }

    //GET A PRODUCT BY CATEGORY NAME
    @GetMapping("/category/name/{name}")
    public List<Product> getProductByCategoryName (@PathVariable String name) {
        return productService.getProductByCategoryName(name);
    }

    //GET PRODUCT BY PRICE
    @GetMapping("/price/{price}")
    public List<Product> getProductByPrice(@PathVariable double price) {
        return productService.getProductByPrice(price);
    }

    //GET PRODUCT BY NAME
    @GetMapping("/name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }


    //UPDATE THE ENTIRE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct) {

        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
    }

    //DELETE MAPPING
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> removeProductById(@PathVariable long id) {
        //CREATE INSTANCE WITH PRODUCT SERVICE
        Product existingProduct = productService.getProductById(id);

        //CHECK IF PRODUCT DOESN'T EXIST
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        //DELETE THE PRODUCT WITH THE PRODUCT SERVICE NOT INSTANCE
        productService.deleteProduct(id);

        //RETURN SUCCESSFUL DELETE
        return ResponseEntity.noContent().build();
    }

    //TAX PRICE
    @GetMapping("/tax/{price}")
    public double getPriceWithTax (@PathVariable double price) {
        //RETURN PRODUCTSERVICE CALCULATE WITH PRICE
        return productService.calculatePriceWithTax(price);
    }

    //APP CONFIG
    @GetMapping("/app-config")
    public AppConfig getAppConfig () {
        return appConfig;
    }
}
