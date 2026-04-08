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
        // Récupérer l'utilisateur connecté
        User user = (User) req.getSession().getAttribute("user");
        
        // Si personne n'est connecté, rediriger vers connexion
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/connexion");
            return;
        }
        
        // Récupérer l'ID de l'annonce à supprimer
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/accueil");
            return;
        }
        
        int id = Integer.parseInt(idParam);
        
        // Récupérer l'annonce
        Annonce annonce = annonceDAO.trouverParId(id);
        
        // Vérification 1 : L'annonce existe-t-elle ?
        if (annonce == null) {
            req.setAttribute("erreur", "Cette annonce n'existe pas");
            req.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(req, resp);
            return;
        }
        
        // Vérification 2 : L'utilisateur est-il le propriétaire ?
        if (annonce.getUtilisateurId() != user.getId()) {
            // Tentative de suppression d'une annonce qui ne lui appartient pas
            req.setAttribute("erreur", "Vous ne pouvez pas supprimer l'annonce d'un autre utilisateur !");
            req.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(req, resp);
            return;
        }
        
        // Vérification 3 : L'utilisateur est-il admin ? (optionnel, peut supprimer toutes les annonces)
        // Décommentez les 3 lignes suivantes si vous voulez qu'un admin puisse tout supprimer
        // if ("admin".equals(user.getRole()) || annonce.getUtilisateurId() == user.getId()) {
        //     annonceDAO.supprimer(id);
        // }
        
        // Si toutes les vérifications sont OK, on supprime
        annonceDAO.supprimer(id);
        
        // Message de confirmation
        req.getSession().setAttribute("message", "Annonce supprimée avec succès");
        resp.sendRedirect(req.getContextPath() + "/accueil");
    }
}
