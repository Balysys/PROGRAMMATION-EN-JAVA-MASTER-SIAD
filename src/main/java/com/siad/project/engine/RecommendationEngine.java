package com.siad.project.engine;

import com.siad.project.model.Recommendation;
import com.siad.project.dao.SaleDAO;
import com.siad.project.model.Sale;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationEngine {

    /**
     * Generate all recommendations based on sales data
     */
    public static List<Recommendation> generateRecommendations() {
        List<Recommendation> recommendations = new ArrayList<>();

        // Get all sales
        List<Sale> allSales = SaleDAO.getAllSales();
        
        if (allSales.isEmpty()) {
            return recommendations;
        }

        // Analyze by region
        Map<String, List<Sale>> salesByRegion = groupSalesByRegion(allSales);
        
        for (String region : salesByRegion.keySet()) {
            List<Sale> regionSales = salesByRegion.get(region);
            Recommendation rec = analyzeRegion(region, regionSales);
            if (rec != null) {
                recommendations.add(rec);
            }
        }

        return recommendations;
    }

    /**
     * Group sales by region
     */
    private static Map<String, List<Sale>> groupSalesByRegion(List<Sale> sales) {
        Map<String, List<Sale>> grouped = new HashMap<>();
        for (Sale sale : sales) {
            grouped.computeIfAbsent(sale.getRegion(), k -> new ArrayList<>()).add(sale);
        }
        return grouped;
    }

    /**
     * Analyze region and generate recommendation
     */
    private static Recommendation analyzeRegion(String region, List<Sale> regionSales) {
        if (regionSales.size() < 2) {
            return null;
        }

        // Calculate current month revenue
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);
        double currentMonthRevenue = 0;
        double previousMonthRevenue = 0;

        for (Sale sale : regionSales) {
            LocalDate saleDate = sale.getSaleDate();
            double saleAmount = sale.getQuantity() * sale.getPrice();

            if (saleDate.isAfter(monthStart.minusDays(1)) && saleDate.isBefore(today.plusDays(1))) {
                currentMonthRevenue += saleAmount;
            } else if (saleDate.isAfter(monthStart.minusMonths(1).minusDays(1)) 
                    && saleDate.isBefore(monthStart)) {
                previousMonthRevenue += saleAmount;
            }
        }

        // Rule 1: Sales decrease > 15%
        if (previousMonthRevenue > 0) {
            double changePercent = ((currentMonthRevenue - previousMonthRevenue) / previousMonthRevenue) * 100;
            
            if (changePercent < -15) {
                return new Recommendation(
                    region,
                    "Apply 10% discount in this region",
                    String.format("Sales decreased by %.2f%% compared to last month", Math.abs(changePercent)),
                    "HIGH",
                    0.85
                );
            }
            
            // Rule 2: Sales increase > 20%
            if (changePercent > 20) {
                return new Recommendation(
                    region,
                    "Increase stock levels and expand marketing budget",
                    String.format("Sales increased by %.2f%% - high demand detected", changePercent),
                    "HIGH",
                    0.90
                );
            }
        }

        // Rule 3: Stagnant sales
        if (currentMonthRevenue > 0 && currentMonthRevenue < 100000) {
            return new Recommendation(
                region,
                "Launch promotional campaign and social media blitz",
                "Sales are stagnant - need to boost visibility and engagement",
                "MEDIUM",
                0.70
            );
        }

        return null;
    }

    /**
     * Generate KPI-based recommendations
     */
    public static List<Recommendation> generateKPIRecommendations(double totalRevenue, double growthRate) {
        List<Recommendation> recommendations = new ArrayList<>();

        if (growthRate < -10) {
            recommendations.add(new Recommendation(
                "GLOBAL",
                "Review pricing strategy and competitive positioning",
                "Negative growth trend detected across all sales",
                "HIGH",
                0.80
            ));
        }

        if (growthRate > 30) {
            recommendations.add(new Recommendation(
                "GLOBAL",
                "Scale operations and expand to new markets",
                "Exceptional growth - opportunity for expansion",
                "HIGH",
                0.95
            ));
        }

        return recommendations;
    }
}
