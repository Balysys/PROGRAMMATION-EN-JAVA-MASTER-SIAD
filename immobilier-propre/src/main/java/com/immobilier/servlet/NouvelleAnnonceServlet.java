package com.immobilier.servlet;
import com.immobilier.dao.AnnonceDAO;
import com.immobilier.model.Annonce;
import com.immobilier.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
@WebServlet("/nouvelle-annonce")
public class NouvelleAnnonceServlet extends HttpServlet {
    private AnnonceDAO annonceDAO = new AnnonceDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/nouvelle-annonce.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        BigDecimal prix = new BigDecimal(req.getParameter("prix"));
        int surface = Integer.parseInt(req.getParameter("surface"));
        int piece = Integer.parseInt(req.getParameter("piece"));
        String adresse = req.getParameter("adresse");
        String ville = req.getParameter("ville");
        String codePostal = req.getParameter("codePostal");
        String type = req.getParameter("type");
        Annonce a = new Annonce(titre, description, prix, surface, piece, adresse, ville, codePostal, type, user.getId());
        if (annonceDAO.ajouter(a)) resp.sendRedirect(req.getContextPath() + "/accueil");
        else { req.setAttribute("erreur", "Erreur lors de l'ajout"); doGet(req, resp); }
    }
}
