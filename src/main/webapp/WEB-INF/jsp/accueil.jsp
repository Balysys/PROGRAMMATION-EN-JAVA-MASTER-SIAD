<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.immobilier.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("connexion");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Immobilier</title>
    <style>
        body { font-family: Arial; margin: 50px; }
        .info { background: #f0f0f0; padding: 15px; border-radius: 8px; }
    </style>
</head>
<body>
    <h1>Bienvenue, <%= user.getNom() %> !</h1>
    <div class="info">
        <p><strong>Email :</strong> <%= user.getEmail() %></p>
        <p><strong>Rôle :</strong> <%= user.getRole() %></p>
        <p><strong>Date d'inscription :</strong> <%= user.getDateInscription() %></p>
    </div>
    <a href="deconnexion">Déconnexion</a>
</body>
</html>
