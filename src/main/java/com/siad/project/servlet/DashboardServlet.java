package com.siad.project.servlet;

import com.siad.project.engine.AnalyticsEngine;
import com.siad.project.engine.RecommendationEngine;
import com.siad.project.model.KPI;
import com.siad.project.model.Recommendation;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check if user is logged in
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        // Calculate KPIs
        KPI kpi = AnalyticsEngine.calculateKPIs();
        
        // Generate recommendations
        List<Recommendation> recommendations = RecommendationEngine.generateRecommendations();

        // Set attributes
        request.setAttribute("kpi", kpi);
        request.setAttribute("recommendations", recommendations);

        // Forward to dashboard JSP
        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }
}
