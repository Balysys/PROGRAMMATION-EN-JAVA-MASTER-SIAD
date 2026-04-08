package com.immobilier.servlet;

import com.immobilier.dao.UserDAO;
import com.immobilier.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        String confirmMdp = req.getParameter("confirmMdp");

        if (!motDePasse.equals(confirmMdp)) {
            req.setAttribute("erreur", "Les mots de passe ne correspondent pas.");
            req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
            return;
        }

        if (userDAO.trouverParEmail(email) != null) {
            req.setAttribute("erreur", "Cet email est déjà utilisé.");
            req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
            return;
        }

        String hashed = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
        User user = new User(nom, email, hashed, "utilisateur");

        if (userDAO.inscrire(user)) {
            resp.sendRedirect(req.getContextPath() + "/connexion?inscription=ok");
        } else {
            req.setAttribute("erreur", "Erreur lors de l'inscription.");
            req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
        }
    }
}
