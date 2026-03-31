-- =====================================================
-- SIAD Sales Data Analytics Dashboard Database Script
-- =====================================================
-- Create Database
CREATE DATABASE IF NOT EXISTS siad_sales_db;
USE siad_sales_db;

-- =====================================================
-- USERS TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL COMMENT 'ADMIN or ANALYST',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- PRODUCTS TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_category (category)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- SALES TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    region VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    sale_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_name) REFERENCES products(name),
    INDEX idx_region (region),
    INDEX idx_date (sale_date),
    INDEX idx_product (product_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- INSERT DEMO DATA
-- =====================================================

-- Create demo users (password: admin123 - SHA256 hashed)
INSERT INTO users (username, password, role) VALUES
('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e1c33deb3341beb9652a02f45e', 'ADMIN'),
('analyst1', '240be518fabd2724ddb6f04eeb1da5967448d7e1c33deb3341beb9652a02f45e', 'ANALYST'),
('analyst2', '240be518fabd2724ddb6f04eeb1da5967448d7e1c33deb3341beb9652a02f45e', 'ANALYST');

-- Insert product categories
INSERT INTO products (name, category) VALUES
('Laptop HP', 'Electronics'),
('Smartphone Samsung', 'Electronics'),
('Shirt Blue', 'Clothing'),
('Pants Black', 'Clothing'),
('Shoes Nike', 'Footwear'),
('Coffee Maker', 'Home Appliances'),
('Microwave LG', 'Home Appliances'),
('Rice 5kg', 'Food'),
('Sugar 2kg', 'Food'),
('Oil 1L', 'Food');

-- Insert demo sales data
INSERT INTO sales (product_name, region, quantity, price, sale_date) VALUES
('Laptop HP', 'Parakou', 5, 450000, '2024-01-15'),
('Smartphone Samsung', 'Parakou', 10, 180000, '2024-01-20'),
('Shirt Blue', 'Cotonou', 20, 15000, '2024-01-22'),
('Pants Black', 'Cotonou', 15, 25000, '2024-02-05'),
('Shoes Nike', 'Porto-Novo', 8, 45000, '2024-02-10'),
('Coffee Maker', 'Parakou', 12, 35000, '2024-02-15'),
('Microwave LG', 'Abomey', 6, 55000, '2024-02-18'),
('Rice 5kg', 'Cotonou', 30, 8000, '2024-03-01'),
('Sugar 2kg', 'Parakou', 25, 4000, '2024-03-05'),
('Oil 1L', 'Porto-Novo', 20, 2500, '2024-03-10'),
('Laptop HP', 'Cotonou', 8, 450000, '2024-03-15'),
('Smartphone Samsung', 'Abomey', 15, 180000, '2024-03-20');

-- =====================================================
-- CREATE VIEWS FOR ANALYTICS
-- =====================================================

-- Total Sales by Region
CREATE OR REPLACE VIEW sales_by_region AS
SELECT 
    region,
    COUNT(*) as total_sales,
    SUM(quantity) as total_quantity,
    SUM(quantity * price) as total_revenue,
    AVG(quantity * price) as avg_sale_value
FROM sales
GROUP BY region;

-- Total Sales by Product
CREATE OR REPLACE VIEW sales_by_product AS
SELECT 
    product_name,
    COUNT(*) as total_sales,
    SUM(quantity) as total_quantity,
    SUM(quantity * price) as total_revenue
FROM sales
GROUP BY product_name;

-- Monthly Sales Summary
CREATE OR REPLACE VIEW monthly_sales AS
SELECT 
    DATE_FORMAT(sale_date, '%Y-%m') as month,
    region,
    COUNT(*) as sales_count,
    SUM(quantity) as total_quantity,
    SUM(quantity * price) as total_revenue
FROM sales
GROUP BY DATE_FORMAT(sale_date, '%Y-%m'), region;

-- =====================================================
-- DATABASE INITIALIZATION COMPLETE
-- =====================================================
COMMIT;
