<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Nouvelle annonce</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-success text-white"><h3>Nouvelle annonce</h3></div>
                <div class="card-body">
                    <% if(request.getAttribute("erreur") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
                    <% } %>
                    <form action="nouvelle-annonce" method="post">
                        <div class="mb-3"><label>Titre</label><input type="text" name="titre" class="form-control" required></div>
                        <div class="mb-3"><label>Description</label><textarea name="description" rows="4" class="form-control" required></textarea></div>
                        <div class="row">
                            <div class="col-md-6"><div class="mb-3"><label>Prix (€)</label><input type="number" step="0.01" name="prix" class="form-control" required></div></div>
                            <div class="col-md-6"><div class="mb-3"><label>Surface (m²)</label><input type="number" name="surface" class="form-control" required></div></div>
                        </div>
                        <div class="row">
                            <div class="col-md-6"><div class="mb-3"><label>Pièces</label><input type="number" name="piece" class="form-control" required></div></div>
                            <div class="col-md-6"><div class="mb-3"><label>Type</label><select name="type" class="form-select"><option value="vente">Vente</option><option value="location">Location</option></select></div></div>
                        </div>
                        <div class="mb-3"><label>Adresse</label><input type="text" name="adresse" class="form-control" required></div>
                        <div class="row">
                            <div class="col-md-6"><div class="mb-3"><label>Ville</label><input type="text" name="ville" class="form-control" required></div></div>
                            <div class="col-md-6"><div class="mb-3"><label>Code postal</label><input type="text" name="codePostal" class="form-control" required></div></div>
                        </div>
                        <button type="submit" class="btn btn-success">Publier</button>
                        <a href="accueil" class="btn btn-secondary">Annuler</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
