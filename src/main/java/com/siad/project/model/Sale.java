package com.siad.project.model;

import java.time.LocalDate;

public class Sale {
    private int id;
    private String productName;
    private String region;
    private int quantity;
    private double price;
    private LocalDate saleDate;
    private double totalAmount;

    public Sale() {}

    public Sale(String productName, String region, int quantity, double price, LocalDate saleDate) {
        this.productName = productName;
        this.region = region;
        this.quantity = quantity;
        this.price = price;
        this.saleDate = saleDate;
        this.totalAmount = quantity * price;
    }

    public Sale(int id, String productName, String region, int quantity, double price, LocalDate saleDate) {
        this.id = id;
        this.productName = productName;
        this.region = region;
        this.quantity = quantity;
        this.price = price;
        this.saleDate = saleDate;
        this.totalAmount = quantity * price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
