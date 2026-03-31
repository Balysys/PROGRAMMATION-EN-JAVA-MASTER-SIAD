package com.siad.project.model;

public class KPI {
    private double totalSales;
    private double totalRevenue;
    private int totalQuantity;
    private String topProduct;
    private String bestRegion;
    private double growthRate;
    private double monthlyChange;

    public KPI() {}

    public KPI(double totalSales, double totalRevenue, int totalQuantity, 
               String topProduct, String bestRegion, double growthRate, double monthlyChange) {
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
        this.totalQuantity = totalQuantity;
        this.topProduct = topProduct;
        this.bestRegion = bestRegion;
        this.growthRate = growthRate;
        this.monthlyChange = monthlyChange;
    }

    // Getters and Setters
    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTopProduct() {
        return topProduct;
    }

    public void setTopProduct(String topProduct) {
        this.topProduct = topProduct;
    }

    public String getBestRegion() {
        return bestRegion;
    }

    public void setBestRegion(String bestRegion) {
        this.bestRegion = bestRegion;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getMonthlyChange() {
        return monthlyChange;
    }

    public void setMonthlyChange(double monthlyChange) {
        this.monthlyChange = monthlyChange;
    }
}
