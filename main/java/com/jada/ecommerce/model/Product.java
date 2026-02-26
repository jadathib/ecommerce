package com.jada.ecommerce.model;

public class Product {

    private Long id;
    private String name;
    private double price;
    private int stockQuantity;

    // constructor
    public Product(Long id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
}