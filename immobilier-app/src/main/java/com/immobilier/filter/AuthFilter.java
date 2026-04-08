package com.immobilier.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/nouvelle-annonce", "/supprimer-annonce", "/modifier-annonce"})
public class AuthFilter implements Filter {
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        // Vérifier si l'utilisateur est connecté
        if (session == null || session.getAttribute("user") == null) {
            // Rediriger vers la page de connexion
            response.sendRedirect(request.getContextPath() + "/connexion");
        } else {
            chain.doFilter(req, res);
        }
    }
    
    public void init(FilterConfig fConfig) {}
    public void destroy() {}
}
