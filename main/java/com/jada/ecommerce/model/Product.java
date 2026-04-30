package com.jada.ecommerce.model;

import jakarta.persistence.*;

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

    //SETTERS
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
}