package com.immobilier.servlet;
import com.immobilier.dao.AnnonceDAO;
import com.immobilier.model.Annonce;
import com.immobilier.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/supprimer-annonce")
public class SupprimerAnnonceServlet extends HttpServlet {
    private AnnonceDAO annonceDAO = new AnnonceDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Annonce a = annonceDAO.trouverParId(id);
        User user = (User) req.getSession().getAttribute("user");
        if (a != null && (a.getUtilisateurId() == user.getId() || "admin".equals(user.getRole()))) {
            annonceDAO.supprimer(id);
        }
        resp.sendRedirect(req.getContextPath() + "/accueil");
    }
}
