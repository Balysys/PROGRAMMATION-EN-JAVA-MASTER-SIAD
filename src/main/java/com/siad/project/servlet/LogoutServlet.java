package com.siad.project.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("userRole");
        session.invalidate();
        
        response.sendRedirect("login");
    }
}
