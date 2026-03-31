package com.siad.project.servlet;

import com.siad.project.engine.RecommendationEngine;
import com.siad.project.model.Recommendation;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RecommendationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check session
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        // Generate recommendations
        List<Recommendation> recommendations = RecommendationEngine.generateRecommendations();

        request.setAttribute("recommendations", recommendations);
        request.getRequestDispatcher("/jsp/recommendations.jsp").forward(request, response);
    }
}
