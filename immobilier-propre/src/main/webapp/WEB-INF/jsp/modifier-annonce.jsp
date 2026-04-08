<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.immobilier.model.Annonce"%>
<% Annonce a = (Annonce) request.getAttribute("annonce"); if(a==null) response.sendRedirect("accueil"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Modifier annonce</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container mt-5"><div class="card"><div class="card-header bg-warning"><h3>Modifier l'annonce</h3></div>
<div class="card-body"><form action="modifier-annonce" method="post"><input type="hidden" name="id" value="<%= a.getId() %>">
<div class="mb-3"><label>Titre</label><input type="text" name="titre" class="form-control" value="<%= a.getTitre() %>" required></div>
<div class="mb-3"><label>Description</label><textarea name="description" rows="4" class="form-control" required><%= a.getDescription() %></textarea></div>
<div class="row"><div class="col-md-6"><div class="mb-3"><label>Prix</label><input type="number" step="0.01" name="prix" class="form-control" value="<%= a.getPrix() %>" required></div></div>
<div class="col-md-6"><div class="mb-3"><label>Surface</label><input type="number" name="surface" class="form-control" value="<%= a.getSurface() %>" required></div></div></div>
<div class="row"><div class="col-md-6"><div class="mb-3"><label>Pièces</label><input type="number" name="piece" class="form-control" value="<%= a.getPiece() %>" required></div></div>
<div class="col-md-6"><div class="mb-3"><label>Type</label><select name="type" class="form-select"><option value="vente" <%= "vente".equals(a.getType())?"selected":"" %>>Vente</option><option value="location" <%= "location".equals(a.getType())?"selected":"" %>>Location</option></select></div></div></div>
<div class="mb-3"><label>Adresse</label><input type="text" name="adresse" class="form-control" value="<%= a.getAdresse() %>" required></div>
<div class="row"><div class="col-md-6"><div class="mb-3"><label>Ville</label><input type="text" name="ville" class="form-control" value="<%= a.getVille() %>" required></div></div>
<div class="col-md-6"><div class="mb-3"><label>Code postal</label><input type="text" name="codePostal" class="form-control" value="<%= a.getCodePostal() %>" required></div></div></div>
<button type="submit" class="btn btn-primary">Enregistrer</button> <a href="accueil" class="btn btn-secondary">Annuler</a>
</form></div></div></div></body></html>
