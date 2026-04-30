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

    //UPDATE THE ENTIRE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        //CREATE INSTANCE
        Product existingProduct = productService.getProductById(id);

        //CHECK IF IT DOESN'T EXISTS
        if (existingProduct == null) {
            ResponseEntity.notFound().build();
        }

        //UPDATE THE NAME
        existingProduct.setName(updatedProduct.getName());

        //UPDATE THE PRICE
        existingProduct.setPrice(updatedProduct.getPrice());

        //CREATE NEW INSTANCE OF SAVED PRODUCT WITH EXISTING PRODUCT AND SAVE THE PRODUCT
        Product savedProduct = productService.saveProduct(existingProduct);

        //RETURN
        return ResponseEntity.ok(savedProduct);
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
}
