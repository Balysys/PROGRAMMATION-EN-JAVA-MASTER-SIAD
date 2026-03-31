package com.siad.project.dao;

import com.siad.project.model.Sale;
import com.siad.project.utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    /**
     * Add a new sale
     */
    public static boolean addSale(Sale sale) {
        String query = "INSERT INTO sales (product_name, region, quantity, price, sale_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getProductName());
            stmt.setString(2, sale.getRegion());
            stmt.setInt(3, sale.getQuantity());
            stmt.setDouble(4, sale.getPrice());
            stmt.setDate(5, Date.valueOf(sale.getSaleDate()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all sales
     */
    public static List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales ORDER BY sale_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                sales.add(new Sale(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("region"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("sale_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    /**
     * Get sales by region
     */
    public static List<Sale> getSalesByRegion(String region) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales WHERE region = ? ORDER BY sale_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                sales.add(new Sale(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("region"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("sale_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    /**
     * Get sales by date range
     */
    public static List<Sale> getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales WHERE sale_date BETWEEN ? AND ? ORDER BY sale_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                sales.add(new Sale(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("region"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("sale_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    /**
     * Get sales by product
     */
    public static List<Sale> getSalesByProduct(String productName) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales WHERE product_name = ? ORDER BY sale_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                sales.add(new Sale(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("region"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getDate("sale_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    /**
     * Get total revenue
     */
    public static double getTotalRevenue() {
        String query = "SELECT SUM(quantity * price) as total FROM sales";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Get total sales count
     */
    public static int getTotalSalesCount() {
        String query = "SELECT COUNT(*) as count FROM sales";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Update a sale
     */
    public static boolean updateSale(Sale sale) {
        String query = "UPDATE sales SET product_name = ?, region = ?, quantity = ?, price = ?, sale_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getProductName());
            stmt.setString(2, sale.getRegion());
            stmt.setInt(3, sale.getQuantity());
            stmt.setDouble(4, sale.getPrice());
            stmt.setDate(5, Date.valueOf(sale.getSaleDate()));
            stmt.setInt(6, sale.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a sale
     */
    public static boolean deleteSale(int id) {
        String query = "DELETE FROM sales WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
