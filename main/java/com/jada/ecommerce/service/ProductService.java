package com.jada.ecommerce.service;

import com.jada.ecommerce.config.AppConfig;
import com.jada.ecommerce.exception.ResourceNotFoundException;
import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final AppConfig appConfig;

    public ProductService(ProductRepository productRepository, AppConfig appConfig) {
        this.productRepository = productRepository;
        this.appConfig = appConfig;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + id
                ));
    }

    //VOID BECAUSE WE ARE DELETING AND NOT RETURNING ANYTHING
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //TAX CALC
    public double calculatePriceWithTax(double price) {
        return price + (price * appConfig.getTaxRate());
    }

    //FIND BY PRICE
    public List<Product> getProductByPrice (double price) {
        return productRepository.findByPrice(price);
    }

    //FIND BY NAME
    public List<Product> getProductByName(String name) {
        //CALL THE REPOSITORY OF THE PRODUCTS
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);

        //IF YOU CAN'T FIND ANY
        if (products.isEmpty()) {
            //THROW THE EXCEPTION
            throw new ResourceNotFoundException("No products found containing: " + name);
        }
        //RETURN THE PRODUCTS
        return products;
    }

    //FIND BY CATEGORY
    public List<Product> getProductByCategory (Category category) {
        List<Product> products = productRepository.findByCategory(category);
        return products;
    }
}