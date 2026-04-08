<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.immobilier.model.User, com.immobilier.model.Annonce, java.util.List"%>
<%
User user = (User) session.getAttribute("user");
if(user==null) response.sendRedirect("connexion");
List<Annonce> annonces = (List<Annonce>) request.getAttribute("annonces");
%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Accueil</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><nav class="navbar navbar-dark bg-dark"><div class="container"><a class="navbar-brand" href="#">🏠 Immobilier</a>
<ul class="navbar-nav ms-auto flex-row"><li class="nav-item me-3"><a class="nav-link" href="nouvelle-annonce">➕ Déposer</a></li>
<li class="nav-item me-3"><span class="nav-link">👋 <%= user.getNom() %></span></li>
<li class="nav-item"><a class="nav-link" href="deconnexion">🚪 Déconnexion</a></li></ul></div></nav>
<div class="container mt-4"><h2>Bienvenue <%= user.getNom() %></h2><p>Email: <%= user.getEmail() %> | Rôle: <%= user.getRole() %></p><hr><h3>Annonces</h3><div class="row">
<% if(annonces!=null && !annonces.isEmpty()){ for(Annonce a: annonces){ %>
<div class="col-md-4 mb-3"><div class="card"><div class="card-body"><h5 class="card-title"><%= a.getTitre() %></h5>
<p class="card-text"><%= a.getDescription().length()>100? a.getDescription().substring(0,100)+"...": a.getDescription() %></p>
<p><%= a.getPrix() %> € | <%= a.getSurface() %> m² | <%= a.getPiece() %> pièces</p>
<p><%= a.getVille() %> - <%= a.getType() %></p>
<% if(user.getId()==a.getUtilisateurId() || "admin".equals(user.getRole())){ %>
<a href="modifier-annonce?id=<%= a.getId() %>" class="btn btn-sm btn-warning">Modifier</a>
<a href="supprimer-annonce?id=<%= a.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Supprimer ?')">Supprimer</a>
<% } %></div></div></div>
<% } } else { %><div class="alert alert-info">Aucune annonce. <a href="nouvelle-annonce">Publiez la première !</a></div><% } %>
</div></div></body></html>
