package com.siad.project.servlet;

import com.siad.project.dao.SaleDAO;
import com.siad.project.model.Sale;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SalesServlet extends HttpServlet {
    
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

        // Get filter parameters
        String region = request.getParameter("region");
        String action = request.getParameter("action");

        List<Sale> sales;
        
        if (region != null && !region.isEmpty()) {
            sales = SaleDAO.getSalesByRegion(region);
        } else {
            sales = SaleDAO.getAllSales();
        }

        request.setAttribute("sales", sales);
        request.setAttribute("selectedRegion", region != null ? region : "");

        request.getRequestDispatcher("/jsp/sales.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String productName = request.getParameter("productName");
            String region = request.getParameter("region");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            LocalDate saleDate = LocalDate.parse(request.getParameter("saleDate"));

            Sale sale = new Sale(productName, region, quantity, price, saleDate);
            SaleDAO.addSale(sale);

            request.setAttribute("message", "Sale added successfully!");
        } else if ("delete".equals(action)) {
            int saleId = Integer.parseInt(request.getParameter("saleId"));
            SaleDAO.deleteSale(saleId);
            request.setAttribute("message", "Sale deleted successfully!");
        }

        // Redirect to sales list
        response.sendRedirect("sales");
    }
}
