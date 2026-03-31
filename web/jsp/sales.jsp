<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.siad.project.model.Sale" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siad.project.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIAD - Sales Data</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        
        List<Sale> sales = (List<Sale>) request.getAttribute("sales");
        String selectedRegion = (String) request.getAttribute("selectedRegion");
    %>
    
    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <aside class="sidebar">
            <div class="logo">
                <h3>📊 SIAD</h3>
                <p>Sales Analytics</p>
            </div>
            
            <nav class="nav-menu">
                <a href="dashboard" class="nav-item">Dashboard</a>
                <a href="sales" class="nav-item active">Sales Data</a>
                <a href="recommendations" class="nav-item">Recommendations</a>
                <a href="analytics" class="nav-item">Analytics</a>
            </nav>
            
            <div class="user-info">
                <p>Logged in as: <strong><%= user.getUsername() %></strong></p>
                <a href="logout" class="btn-logout">Logout</a>
            </div>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="top-bar">
                <h1>Sales Data Management</h1>
            </header>

            <div class="dashboard-content">
                <!-- Add Sale Form -->
                <section class="add-sale-section">
                    <h2>Add New Sale</h2>
                    <form method="POST" action="sales" class="sale-form">
                        <input type="hidden" name="action" value="add">
                        
                        <div class="form-group">
                            <label for="productName">Product Name:</label>
                            <input type="text" id="productName" name="productName" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="region">Region:</label>
                            <select id="region" name="region" required>
                                <option value="">Select Region</option>
                                <option value="Parakou">Parakou</option>
                                <option value="Cotonou">Cotonou</option>
                                <option value="Porto-Novo">Porto-Novo</option>
                                <option value="Abomey">Abomey</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="quantity">Quantity:</label>
                            <input type="number" id="quantity" name="quantity" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="price">Price (CFA):</label>
                            <input type="number" id="price" name="price" step="0.01" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="saleDate">Sale Date:</label>
                            <input type="date" id="saleDate" name="saleDate" required>
                        </div>
                        
                        <button type="submit" class="btn-primary">Add Sale</button>
                    </form>
                </section>

                <!-- Sales Table -->
                <section class="sales-table-section">
                    <h2>Sales Records</h2>
                    
                    <form method="GET" action="sales" class="filter-form">
                        <label for="regionFilter">Filter by Region:</label>
                        <select id="regionFilter" name="region">
                            <option value="">All Regions</option>
                            <option value="Parakou" <%= "Parakou".equals(selectedRegion) ? "selected" : "" %>>Parakou</option>
                            <option value="Cotonou" <%= "Cotonou".equals(selectedRegion) ? "selected" : "" %>>Cotonou</option>
                            <option value="Porto-Novo" <%= "Porto-Novo".equals(selectedRegion) ? "selected" : "" %>>Porto-Novo</option>
                            <option value="Abomey" <%= "Abomey".equals(selectedRegion) ? "selected" : "" %>>Abomey</option>
                        </select>
                        <button type="submit" class="btn-secondary">Filter</button>
                    </form>

                    <% if (sales != null && !sales.isEmpty()) { %>
                        <table class="sales-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Product</th>
                                    <th>Region</th>
                                    <th>Quantity</th>
                                    <th>Price (CFA)</th>
                                    <th>Total</th>
                                    <th>Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Sale sale : sales) { %>
                                    <tr>
                                        <td><%= sale.getId() %></td>
                                        <td><%= sale.getProductName() %></td>
                                        <td><%= sale.getRegion() %></td>
                                        <td><%= sale.getQuantity() %></td>
                                        <td><%= String.format("%.2f", sale.getPrice()) %></td>
                                        <td><%= String.format("%.2f", sale.getTotalAmount()) %></td>
                                        <td><%= sale.getSaleDate() %></td>
                                        <td>
                                            <form method="POST" action="sales" style="display:inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="saleId" value="<%= sale.getId() %>">
                                                <button type="submit" class="btn-delete" onclick="return confirm('Delete this sale?');">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    <% } else { %>
                        <p class="no-data">No sales records found.</p>
                    <% } %>
                </section>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/js/sales.js"></script>
</body>
</html>
