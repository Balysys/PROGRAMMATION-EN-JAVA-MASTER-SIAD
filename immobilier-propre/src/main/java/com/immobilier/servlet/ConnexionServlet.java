package com.immobilier.servlet;
import com.immobilier.dao.UserDAO;
import com.immobilier.model.User;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String mdp = req.getParameter("motDePasse");
        User user = userDAO.trouverParEmail(email);
        if (user != null && BCrypt.checkpw(mdp, user.getMotDePasse())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/accueil");
        } else {
            req.setAttribute("erreur", "Email ou mot de passe incorrect");
            doGet(req, resp);
        }
    }
}
