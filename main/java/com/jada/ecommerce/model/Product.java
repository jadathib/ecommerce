package com.jada.ecommerce.model;

import jakarta.persistence.*;

import java.security.PublicKey;

//MAPS TO A DATABASE TABLE
@Entity
@Table(name = "product")
public class Product {

    //GENERATES AN ID VALUE
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    //MANY PRODUCTS TO ONE CATEGORY
    //ADDS CATEGORY_ID COLUMN TO PRODUCT TABLE
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {

    }

    // constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory () {
        return category;
    }

    //SETTERS
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}