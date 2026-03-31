<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.siad.project.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIAD - Analytics & Reports</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
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
                <a href="sales" class="nav-item">Sales Data</a>
                <a href="recommendations" class="nav-item">Recommendations</a>
                <a href="analytics" class="nav-item active">Analytics</a>
            </nav>
            
            <div class="user-info">
                <p>Logged in as: <strong><%= user.getUsername() %></strong></p>
                <a href="logout" class="btn-logout">Logout</a>
            </div>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="top-bar">
                <h1>📈 Advanced Analytics & Reports</h1>
            </header>

            <div class="dashboard-content">
                <!-- Charts Section -->
                <section class="charts-section">
                    <h2>Sales Performance Visualizations</h2>
                    
                    <div class="chart-container">
                        <h3>Revenue by Region (Pie Chart)</h3>
                        <canvas id="regionPieChart"></canvas>
                    </div>

                    <div class="chart-container">
                        <h3>Sales by Product (Bar Chart)</h3>
                        <canvas id="productBarChart"></canvas>
                    </div>

                    <div class="chart-container">
                        <h3>Monthly Trend (Line Chart)</h3>
                        <canvas id="trendChart"></canvas>
                    </div>
                </section>

                <!-- Export Section -->
                <section class="export-section">
                    <h2>Reports & Export</h2>
                    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 15px;">
                        <button class="btn-primary" onclick="exportToCSV()">📥 Export to CSV</button>
                        <button class="btn-primary" onclick="exportToPDF()">📄 Export to PDF</button>
                        <button class="btn-primary" onclick="printReport()">🖨️ Print Report</button>
                    </div>
                </section>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/js/analytics.js"></script>
</body>
</html>
