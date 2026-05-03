package com.jada.ecommerce.service;

import com.jada.ecommerce.config.AppConfig;
import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.respository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

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
        return productRepository.findById(id).orElse(null);
    }

    //VOID BECAUSE WE ARE DELETING AND NOT RETURNING ANYTHING
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //TAX CALC
    public double calculatePriceWithTax(double price) {
        return price + (price * appConfig.getTaxRate());
    }
}
