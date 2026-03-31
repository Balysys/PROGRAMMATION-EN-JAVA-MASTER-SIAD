<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.siad.project.model.KPI" %>
<%@ page import="com.siad.project.model.Recommendation" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siad.project.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIAD - Dashboard</title>
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
        
        KPI kpi = (KPI) request.getAttribute("kpi");
        List<Recommendation> recommendations = (List<Recommendation>) request.getAttribute("recommendations");
    %>
    
    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <aside class="sidebar">
            <div class="logo">
                <h3>📊 SIAD</h3>
                <p>Sales Analytics</p>
            </div>
            
            <nav class="nav-menu">
                <a href="dashboard" class="nav-item active">Dashboard</a>
                <a href="sales" class="nav-item">Sales Data</a>
                <a href="recommendations" class="nav-item">Recommendations</a>
                <a href="analytics" class="nav-item">Analytics</a>
            </nav>
            
            <div class="user-info">
                <p>Logged in as: <strong><%= user.getUsername() %></strong></p>
                <p>Role: <%= user.getRole() %></p>
                <a href="logout" class="btn-logout">Logout</a>
            </div>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="top-bar">
                <h1>Dashboard - Sales Performance Overview</h1>
            </header>

            <div class="dashboard-content">
                <!-- KPI Cards -->
                <section class="kpi-section">
                    <h2>Key Performance Indicators</h2>
                    <div class="kpi-grid">
                        <div class="kpi-card">
                            <h3>Total Sales</h3>
                            <p class="kpi-value"><%= kpi.getTotalSales() %></p>
                        </div>
                        <div class="kpi-card">
                            <h3>Total Revenue</h3>
                            <p class="kpi-value">CFA <%= String.format("%.2f", kpi.getTotalRevenue()) %></p>
                        </div>
                        <div class="kpi-card">
                            <h3>Total Units Sold</h3>
                            <p class="kpi-value"><%= kpi.getTotalQuantity() %></p>
                        </div>
                        <div class="kpi-card">
                            <h3>Growth Rate</h3>
                            <p class="kpi-value <%= kpi.getGrowthRate() >= 0 ? "positive" : "negative" %>">
                                <%= String.format("%.2f%%", kpi.getGrowthRate()) %>
                            </p>
                        </div>
                    </div>
                </section>

                <!-- Top Performers -->
                <section class="performers-section">
                    <div class="performer-card">
                        <h3>🏆 Top Product</h3>
                        <p><%= kpi.getTopProduct() %></p>
                    </div>
                    <div class="performer-card">
                        <h3>🌍 Best Region</h3>
                        <p><%= kpi.getBestRegion() %></p>
                    </div>
                    <div class="performer-card">
                        <h3>📈 Monthly Change</h3>
                        <p><%= String.format("%.2f%%", kpi.getMonthlyChange()) %></p>
                    </div>
                </section>

                <!-- Recommendations -->
                <section class="recommendations-section">
                    <h2>💡 Decision Recommendations</h2>
                    <% if (recommendations != null && !recommendations.isEmpty()) { %>
                        <div class="recommendations-list">
                            <% for (Recommendation rec : recommendations) { %>
                                <div class="recommendation-card <%= rec.getPriority().toLowerCase() %>">
                                    <div class="rec-header">
                                        <h3><%= rec.getRecommendation() %></h3>
                                        <span class="priority-badge <%= rec.getPriority().toLowerCase() %>">
                                            <%= rec.getPriority() %>
                                        </span>
                                    </div>
                                    <p><strong>Region:</strong> <%= rec.getRegion() %></p>
                                    <p><strong>Reason:</strong> <%= rec.getReason() %></p>
                                    <p><strong>Impact Score:</strong> <%= String.format("%.2f", rec.getImpactScore()) %></p>
                                </div>
                            <% } %>
                        </div>
                    <% } else { %>
                        <p class="no-data">No recommendations available at this time.</p>
                    <% } %>
                </section>

                <!-- Charts -->
                <section class="charts-section">
                    <div class="chart-container">
                        <canvas id="salesChart"></canvas>
                    </div>
                </section>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
</body>
</html>
