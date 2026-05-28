package com.jada.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //ONE CATEGORY TO MANY PRODUCTS
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Product> products;

    //CONSTRUCTORS
    public Category () {
    }

    public Category (String name) {
        this.name = name;
    }

    //GETTERS & SETTERS

    public Long getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public void setName (String newName) {
        this.name = newName;
    }

    public List<Product> getProducts () {
        return products;
    }

    public void setProducts (List<Product> products) {
        this.products = products;
    }
}
