<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.siad.project.model.Recommendation" %>
<%@ page import="java.util.List" %>
<%@ page import="com.siad.project.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SIAD - Recommendations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        
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
                <a href="dashboard" class="nav-item">Dashboard</a>
                <a href="sales" class="nav-item">Sales Data</a>
                <a href="recommendations" class="nav-item active">Recommendations</a>
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
                <h1>💡 Decision Support Recommendations</h1>
            </header>

            <div class="dashboard-content">
                <section class="recommendations-detailed">
                    <h2>AI-Generated Action Items</h2>
                    
                    <% if (recommendations != null && !recommendations.isEmpty()) { %>
                        <div class="recommendations-grid">
                            <% for (Recommendation rec : recommendations) { %>
                                <div class="recommendation-detailed-card <%= rec.getPriority().toLowerCase() %>">
                                    <div class="rec-priority">
                                        <span class="priority-badge <%= rec.getPriority().toLowerCase() %>">
                                            <%= rec.getPriority() %>
                                        </span>
                                    </div>
                                    
                                    <div class="rec-content">
                                        <h3><%= rec.getRecommendation() %></h3>
                                        <p><strong>Region:</strong> <%= rec.getRegion() %></p>
                                        <p><strong>Reason:</strong> <%= rec.getReason() %></p>
                                    </div>
                                    
                                    <div class="rec-footer">
                                        <p><strong>Impact Score:</strong> <%= String.format("%.0f%%", rec.getImpactScore() * 100) %></p>
                                        <div class="impact-bar">
                                            <div class="impact-fill" style="width: <%= rec.getImpactScore() * 100 %>%;"></div>
                                        </div>
                                    </div>
                                </div>
                            <% } %>
                        </div>
                    <% } else { %>
                        <div class="no-data-box">
                            <p>No recommendations available at this time. Keep monitoring your sales data!</p>
                        </div>
                    <% } %>
                </section>

                <!-- Recommendation Guidelines -->
                <section class="guidelines">
                    <h2>Guidelines for Using Recommendations</h2>
                    <ul>
                        <li><strong>HIGH Priority:</strong> Implement immediately for maximum impact</li>
                        <li><strong>MEDIUM Priority:</strong> Plan implementation in the next 2 weeks</li>
                        <li><strong>LOW Priority:</strong> Consider for long-term strategy</li>
                        <li><strong>Impact Score:</strong> Percentage likelihood of achieving desired outcome</li>
                    </ul>
                </section>
            </div>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/js/recommendations.js"></script>
</body>
</html>
