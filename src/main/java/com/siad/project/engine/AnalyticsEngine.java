package com.siad.project.engine;

import com.siad.project.dao.SaleDAO;
import com.siad.project.model.KPI;
import com.siad.project.model.Sale;
import java.time.LocalDate;
import java.util.*;

public class AnalyticsEngine {

    /**
     * Calculate KPIs
     */
    public static KPI calculateKPIs() {
        List<Sale> allSales = SaleDAO.getAllSales();
        
        if (allSales.isEmpty()) {
            return new KPI(0, 0, 0, "N/A", "N/A", 0, 0);
        }

        double totalRevenue = 0;
        int totalQuantity = 0;
        Map<String, Double> productRevenue = new HashMap<>();
        Map<String, Double> regionRevenue = new HashMap<>();

        for (Sale sale : allSales) {
            double saleAmount = sale.getQuantity() * sale.getPrice();
            totalRevenue += saleAmount;
            totalQuantity += sale.getQuantity();

            // Track by product
            productRevenue.put(sale.getProductName(), 
                productRevenue.getOrDefault(sale.getProductName(), 0.0) + saleAmount);

            // Track by region
            regionRevenue.put(sale.getRegion(), 
                regionRevenue.getOrDefault(sale.getRegion(), 0.0) + saleAmount);
        }

        // Find top product
        String topProduct = productRevenue.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        // Find best region
        String bestRegion = regionRevenue.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        // Calculate growth rate
        double growthRate = calculateGrowthRate(allSales);
        double monthlyChange = calculateMonthlyChange(allSales);

        return new KPI(
            allSales.size(),
            totalRevenue,
            totalQuantity,
            topProduct,
            bestRegion,
            growthRate,
            monthlyChange
        );
    }

    /**
     * Calculate growth rate (percentage change from first to last month)
     */
    private static double calculateGrowthRate(List<Sale> sales) {
        if (sales.size() < 2) return 0;

        LocalDate firstDate = sales.stream()
                .map(Sale::getSaleDate)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());

        LocalDate lastDate = sales.stream()
                .map(Sale::getSaleDate)
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now());

        double firstMonthRevenue = sales.stream()
                .filter(s -> s.getSaleDate().getMonth() == firstDate.getMonth() 
                        && s.getSaleDate().getYear() == firstDate.getYear())
                .mapToDouble(s -> s.getQuantity() * s.getPrice())
                .sum();

        double lastMonthRevenue = sales.stream()
                .filter(s -> s.getSaleDate().getMonth() == lastDate.getMonth() 
                        && s.getSaleDate().getYear() == lastDate.getYear())
                .mapToDouble(s -> s.getQuantity() * s.getPrice())
                .sum();

        if (firstMonthRevenue == 0) return 0;
        return ((lastMonthRevenue - firstMonthRevenue) / firstMonthRevenue) * 100;
    }

    /**
     * Calculate monthly change (current month vs previous month)
     */
    private static double calculateMonthlyChange(List<Sale> sales) {
        LocalDate today = LocalDate.now();
        LocalDate currentMonthStart = today.withDayOfMonth(1);
        LocalDate previousMonthStart = currentMonthStart.minusMonths(1);

        double currentMonthRevenue = sales.stream()
                .filter(s -> s.getSaleDate().isAfter(currentMonthStart.minusDays(1)))
                .mapToDouble(s -> s.getQuantity() * s.getPrice())
                .sum();

        double previousMonthRevenue = sales.stream()
                .filter(s -> s.getSaleDate().isAfter(previousMonthStart.minusDays(1)) 
                        && s.getSaleDate().isBefore(currentMonthStart))
                .mapToDouble(s -> s.getQuantity() * s.getPrice())
                .sum();

        if (previousMonthRevenue == 0) return 0;
        return ((currentMonthRevenue - previousMonthRevenue) / previousMonthRevenue) * 100;
    }

    /**
     * Get sales trend by region for last 12 months
     */
    public static Map<String, List<Double>> getSalesTrendByRegion() {
        List<Sale> allSales = SaleDAO.getAllSales();
        Map<String, List<Double>> trends = new HashMap<>();

        LocalDate now = LocalDate.now();
        for (int i = 11; i >= 0; i--) {
            LocalDate monthStart = now.minusMonths(i).withDayOfMonth(1);
            LocalDate monthEnd = monthStart.plusMonths(1).minusDays(1);

            for (Sale sale : allSales) {
                LocalDate saleDate = sale.getSaleDate();
                if (!saleDate.isBefore(monthStart) && !saleDate.isAfter(monthEnd)) {
                    String region = sale.getRegion();
                    trends.computeIfAbsent(region, k -> new ArrayList<>());
                    List<Double> regionTrend = trends.get(region);
                    
                    if (regionTrend.size() <= i) {
                        regionTrend.add(i, sale.getQuantity() * sale.getPrice());
                    } else {
                        regionTrend.set(i, regionTrend.get(i) + (sale.getQuantity() * sale.getPrice()));
                    }
                }
            }
        }

        return trends;
    }
}
