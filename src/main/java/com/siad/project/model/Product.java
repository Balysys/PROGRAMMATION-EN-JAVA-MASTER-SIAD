package com.siad.project.model;

public class Product {
    private int id;
    private String name;
    private String category;

    public Product() {}

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
