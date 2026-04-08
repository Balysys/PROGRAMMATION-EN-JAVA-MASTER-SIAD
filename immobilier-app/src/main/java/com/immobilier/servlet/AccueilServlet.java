package com.immobilier.servlet;
import com.immobilier.dao.AnnonceDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
    private AnnonceDAO annonceDAO = new AnnonceDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/connexion");
            return;
        }
        req.setAttribute("annonces", annonceDAO.toutesLesAnnonces());
        req.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(req, resp);
    }
}
