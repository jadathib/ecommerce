package com.jada.ecommerce.service;

import com.jada.ecommerce.config.AppConfig;
import com.jada.ecommerce.exception.ResourceNotFoundException;
import com.jada.ecommerce.model.Category;
import com.jada.ecommerce.model.Product;
import com.jada.ecommerce.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          AppConfig appConfig,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.appConfig = appConfig;
        this.categoryRepository = categoryRepository;
    }

    // ========================
    // SAVE PRODUCT
    // ========================
    public Product saveProduct(Product product) {

        Category category = resolveCategory(product.getCategory());

        product.setCategory(category);

        return productRepository.save(product);
    }

    // ========================
    // UPDATE PRODUCT
    // ========================
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());

        if (updatedProduct.getCategory() != null) {
            Category category = resolveCategory(updatedProduct.getCategory());
            existing.setCategory(category);
        }

        return productRepository.save(existing);
    }

    // ========================
    // GET ALL PRODUCTS
    // ========================
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ========================
    // GET PRODUCT BY ID
    // ========================
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id)
                );
    }

    // ========================
    // DELETE PRODUCT
    // ========================
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ========================
    // TAX CALCULATION
    // ========================
    public double calculatePriceWithTax(double price) {
        return price + (price * appConfig.getTaxRate());
    }

    // ========================
    // FIND BY PRICE
    // ========================
    public List<Product> getProductByPrice(double price) {
        return productRepository.findByPrice(price);
    }

    // ========================
    // FIND BY NAME
    // ========================
    public List<Product> getProductByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found containing: " + name);
        }

        return products;
    }

    // ========================
    // FIND BY CATEGORY ID
    // ========================
    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // ========================
    // FIND BY CATEGORY NAME
    // ========================
    public List<Product> getProductByCategoryName(String name) {
        List<Product> products = productRepository.findByCategory_NameIgnoreCase(name);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No products found containing category: " + name
            );
        }

        return products;
    }

    // ========================
    // HELPER METHOD
    // ========================
    private Category resolveCategory(Category incomingCategory) {

        if (incomingCategory == null) {
            throw new ResourceNotFoundException("Category is required");
        }

        // 🔥 TRY ID FIRST
        if (incomingCategory.getId() != null) {
            return categoryRepository.findById(incomingCategory.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }

        // 🔥 THEN NAME (SAFE CHECK)
        if (incomingCategory.getName() != null &&
                !incomingCategory.getName().trim().isEmpty()) {

            return categoryRepository.findByNameIgnoreCase(
                    incomingCategory.getName().trim()
            ).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }

        throw new ResourceNotFoundException("Category must have id or name");
    }
}