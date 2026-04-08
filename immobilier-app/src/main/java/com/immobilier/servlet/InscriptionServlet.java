package com.immobilier.servlet;
import com.immobilier.dao.UserDAO;
import com.immobilier.model.User;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String mdp = req.getParameter("motDePasse");
        String confirm = req.getParameter("confirmMdp");
        if (!mdp.equals(confirm)) { req.setAttribute("erreur", "Mots de passe différents"); doGet(req, resp); return; }
        if (userDAO.trouverParEmail(email) != null) { req.setAttribute("erreur", "Email déjà utilisé"); doGet(req, resp); return; }
        String hashed = BCrypt.hashpw(mdp, BCrypt.gensalt());
        User user = new User(nom, email, hashed, "utilisateur");
        if (userDAO.inscrire(user)) resp.sendRedirect(req.getContextPath() + "/connexion?inscription=ok");
        else { req.setAttribute("erreur", "Erreur technique"); doGet(req, resp); }
    }
}
