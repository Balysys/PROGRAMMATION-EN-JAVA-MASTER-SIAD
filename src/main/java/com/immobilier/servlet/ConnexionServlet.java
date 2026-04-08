package com.immobilier.servlet;

import com.immobilier.dao.UserDAO;
import com.immobilier.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        User user = userDAO.trouverParEmail(email);
        if (user != null && BCrypt.checkpw(motDePasse, user.getMotDePasse())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/accueil");
        } else {
            req.setAttribute("erreur", "Email ou mot de passe incorrect.");
            req.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(req, resp);
        }
    }
}
