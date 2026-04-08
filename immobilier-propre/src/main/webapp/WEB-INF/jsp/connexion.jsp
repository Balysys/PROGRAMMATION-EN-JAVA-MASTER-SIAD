<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Connexion</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container"><div class="row justify-content-center"><div class="col-md-6"><div class="card mt-5"><div class="card-header bg-primary text-white"><h3>Connexion</h3></div>
<div class="card-body"><% if(request.getAttribute("erreur")!=null){ %><div class="alert alert-danger"><%= request.getAttribute("erreur") %></div><% } %>
<% if(request.getParameter("inscription")!=null){ %><div class="alert alert-success">Inscription réussie ! Connectez-vous.</div><% } %>
<form action="connexion" method="post"><div class="mb-3"><label>Email</label><input type="email" name="email" class="form-control" required></div>
<div class="mb-3"><label>Mot de passe</label><input type="password" name="motDePasse" class="form-control" required></div>
<button type="submit" class="btn btn-primary w-100">Se connecter</button></form>
<hr><p class="text-center">Pas de compte ? <a href="inscription">Inscrivez-vous</a></p>
</div></div></div></div></div></body></html>
