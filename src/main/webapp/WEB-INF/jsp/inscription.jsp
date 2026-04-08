<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription - Immobilier de Chez Nous</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Inscription</h3>
                </div>
                <div class="card-body">
                    <% if(request.getAttribute("erreur") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
                    <% } %>
                    <form action="inscription" method="post">
                        <div class="mb-3">
                            <label class="form-label">Nom complet</label>
                            <input type="text" name="nom" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mot de passe</label>
                            <input type="password" name="motDePasse" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Confirmer mot de passe</label>
                            <input type="password" name="confirmMdp" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">S'inscrire</button>
                    </form>
                    <hr>
                    <p class="text-center">Déjà inscrit ? <a href="connexion">Connectez-vous</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
