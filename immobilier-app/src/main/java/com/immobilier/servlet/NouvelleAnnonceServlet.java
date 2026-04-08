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
        Annonce a = new Annonce();
        a.setTitre(req.getParameter("titre"));
        a.setDescription(req.getParameter("description"));
        a.setPrix(new BigDecimal(req.getParameter("prix")));
        a.setSurface(Integer.parseInt(req.getParameter("surface")));
        a.setPiece(Integer.parseInt(req.getParameter("piece")));
        a.setAdresse(req.getParameter("adresse"));
        a.setVille(req.getParameter("ville"));
        a.setCodePostal(req.getParameter("codePostal"));
        a.setType(req.getParameter("type"));
        a.setUtilisateurId(user.getId());
        if (annonceDAO.ajouter(a)) resp.sendRedirect(req.getContextPath() + "/accueil");
        else { req.setAttribute("erreur", "Erreur lors de l'ajout"); doGet(req, resp); }
    }
}
