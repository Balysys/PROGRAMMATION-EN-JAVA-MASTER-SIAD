package com.immobilier.servlet;
import com.immobilier.dao.AnnonceDAO;
import com.immobilier.model.Annonce;
import com.immobilier.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
@WebServlet("/modifier-annonce")
public class ModifierAnnonceServlet extends HttpServlet {
    private AnnonceDAO annonceDAO = new AnnonceDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Annonce a = annonceDAO.trouverParId(id);
        User user = (User) req.getSession().getAttribute("user");
        if (a == null || (a.getUtilisateurId() != user.getId() && !"admin".equals(user.getRole()))) {
            resp.sendRedirect(req.getContextPath() + "/accueil");
            return;
        }
        req.setAttribute("annonce", a);
        req.getRequestDispatcher("/WEB-INF/jsp/modifier-annonce.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Annonce a = annonceDAO.trouverParId(id);
        User user = (User) req.getSession().getAttribute("user");
        if (a == null || (a.getUtilisateurId() != user.getId() && !"admin".equals(user.getRole()))) {
            resp.sendRedirect(req.getContextPath() + "/accueil");
            return;
        }
        a.setTitre(req.getParameter("titre"));
        a.setDescription(req.getParameter("description"));
        a.setPrix(new BigDecimal(req.getParameter("prix")));
        a.setSurface(Integer.parseInt(req.getParameter("surface")));
        a.setPiece(Integer.parseInt(req.getParameter("piece")));
        a.setAdresse(req.getParameter("adresse"));
        a.setVille(req.getParameter("ville"));
        a.setCodePostal(req.getParameter("codePostal"));
        a.setType(req.getParameter("type"));
        if (annonceDAO.modifier(a)) resp.sendRedirect(req.getContextPath() + "/accueil");
        else { req.setAttribute("erreur", "Erreur modification"); req.setAttribute("annonce", a); doGet(req, resp); }
    }
}
