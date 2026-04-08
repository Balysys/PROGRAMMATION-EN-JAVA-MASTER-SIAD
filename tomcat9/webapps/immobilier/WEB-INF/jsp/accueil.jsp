<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.immobilier.model.User, com.immobilier.model.Annonce, java.util.List"%>
<%
User user = (User) session.getAttribute("user");
if(user == null) response.sendRedirect("connexion");
List<Annonce> annonces = (List<Annonce>) request.getAttribute("annonces");
String message = (String) session.getAttribute("message");
if(message != null) {
    session.removeAttribute("message");
}
String erreur = (String) request.getAttribute("erreur");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Accueil - Immobilier</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">🏠 Immobilier de Chez Nous</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="nouvelle-annonce">📝 Déposer une annonce</a></li>
                <li class="nav-item"><span class="nav-link">👤 <%= user.getNom() %></span></li>
                <li class="nav-item"><a class="nav-link" href="deconnexion">🚪 Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <% if(message != null) { %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <%= message %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    <% } %>
    <% if(erreur != null) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <%= erreur %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    <% } %>
    
    <h1 class="mb-4">Bienvenue, <%= user.getNom() %> !</h1>
    <p class="lead">Email: <%= user.getEmail() %> | Rôle: <%= user.getRole() %></p>
    <hr>
    
    <h2 class="mb-3">📢 Annonces immobilières</h2>
    <div class="row">
        <% if(annonces != null && !annonces.isEmpty()) {
            for(Annonce a : annonces) { %>
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title text-primary"><%= a.getTitre() %></h5>
                            <h6 class="card-subtitle mb-2 text-muted">
                                📍 <%= a.getVille() %> - <%= a.getType() %>
                            </h6>
                            <p class="card-text"><%= a.getDescription().length() > 100 ? a.getDescription().substring(0,100)+"..." : a.getDescription() %></p>
                            <div class="mt-2">
                                <span class="badge bg-success">💰 <%= String.format("%,.0f", a.getPrix()) %> FCFA</span>
                                <span class="badge bg-info">📐 <%= a.getSurface() %> m²</span>
                                <span class="badge bg-secondary">🛏️ <%= a.getPiece() %> pièces</span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between align-items-center mt-2">
                                <small class="text-muted">
                                    👤 Posté par : <strong><%= a.getProprietaireNom() %></strong>
                                </small>
                                <% if(user.getId() == a.getUtilisateurId() || "admin".equals(user.getRole())) { %>
                                    <a href="supprimer-annonce?id=<%= a.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Supprimer cette annonce ?')">🗑️ Supprimer</a>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
        <%  }
        } else { %>
            <div class="col-12">
                <div class="alert alert-info text-center">
                    📭 Aucune annonce pour le moment. 
                    <a href="nouvelle-annonce" class="alert-link">Soyez le premier à en déposer une !</a>
                </div>
            </div>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
