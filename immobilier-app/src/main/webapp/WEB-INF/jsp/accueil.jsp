<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.immobilier.model.User, com.immobilier.model.Annonce, java.util.List"%>
<%
User user = (User) session.getAttribute("user");
List<Annonce> annonces = (List<Annonce>) request.getAttribute("annonces");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Immobilier de Chez Nous</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">🏠 Immobilier de Chez Nous</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <% if(user != null) { %>
                    <li class="nav-item"><a class="nav-link" href="nouvelle-annonce">📝 Déposer une annonce</a></li>
                    <li class="nav-item"><span class="nav-link">👋 <%= user.getNom() %></span></li>
                    <li class="nav-item"><a class="nav-link" href="deconnexion">🚪 Déconnexion</a></li>
                <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="connexion">🔑 Connexion</a></li>
                    <li class="nav-item"><a class="nav-link" href="inscription">📝 Inscription</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h1 class="mb-4">🏠 Annonces immobilières du Bénin</h1>
    
    <% if(user == null) { %>
        <div class="alert alert-info">
            🔐 <a href="connexion">Connectez-vous</a> pour déposer vos annonces !
        </div>
    <% } %>
    
    <div class="row">
        <% if(annonces != null && !annonces.isEmpty()) {
            for(Annonce a : annonces) { %>
                <div class="col-md-4 mb-3">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title text-primary"><%= a.getTitre() %></h5>
                            <h6 class="card-subtitle mb-2 text-muted">📍 <%= a.getVille() %> - <%= a.getType() %></h6>
                            <p class="card-text"><%= a.getDescription().length() > 100 ? a.getDescription().substring(0,100)+"..." : a.getDescription() %></p>
                            <div class="mt-2">
                                <span class="badge bg-success">💰 <%= String.format("%,.0f", a.getPrix()) %> FCFA</span>
                                <span class="badge bg-info">📐 <%= a.getSurface() %> m²</span>
                                <span class="badge bg-secondary">🛏️ <%= a.getPiece() %> pièces</span>
                            </div>
                            <hr>
                            <small class="text-muted">👤 Posté par : ID <%= a.getUtilisateurId() %></small>
                            <% if(user != null && user.getId() == a.getUtilisateurId()) { %>
                                <a href="supprimer-annonce?id=<%= a.getId() %>" class="btn btn-sm btn-danger float-end" onclick="return confirm('Supprimer ?')">🗑️</a>
                            <% } %>
                        </div>
                    </div>
                </div>
        <%  }
        } else { %>
            <div class="alert alert-info">Aucune annonce pour le moment.</div>
        <% } %>
    </div>
</div>
</body>
</html>
