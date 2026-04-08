#!/bin/bash
# ============================================================
# SCRIPT DE DÉMARRAGE POUR L'APPLICATION IMMOBILIER
# ============================================================
# Ce script lance Tomcat et vérifie que tout fonctionne
# À exécuter à chaque ouverture du Codespace
# ============================================================

# ------------------------------------------------------------
# 1. CONFIGURATION DES OPTIONS
# ------------------------------------------------------------

# "set -e" = Si une commande échoue, on arrête tout de suite
# Évite d'avoir des erreurs en cascade
set -e

# "set -u" = Si une variable n'existe pas, on arrête
# Évite d'utiliser des variables mal orthographiées
set -u

# ------------------------------------------------------------
# 2. FONCTION POUR AFFICHER LES ERREURS
# ------------------------------------------------------------

# Cette fonction s'exécute automatiquement quand une erreur arrive
# $1 = numéro de la ligne où l'erreur s'est produite
error_handler() {
    echo ""
    echo "============================================================"
    echo "❌ UNE ERREUR S'EST PRODUITE"
    echo "============================================================"
    echo "Ligne de l'erreur : $1"
    echo ""
    echo "Solutions possibles :"
    echo "1. Vérifie que MariaDB est installé : sudo service mariadb status"
    echo "2. Vérifie que Tomcat existe : ls -la tomcat9/"
    echo "3. Regarde les logs : cat tomcat9/logs/catalina.out | tail -30"
    echo "============================================================"
    exit 1
}

# On enregistre la fonction error_handler pour qu'elle soit appelée à chaque erreur
trap 'error_handler $LINENO' ERR

# ------------------------------------------------------------
# 3. AFFICHAGE DU MESSAGE DE BIENVENUE
# ------------------------------------------------------------

echo ""
echo "============================================================"
echo "🚀 DÉMARRAGE DE L'APPLICATION IMMOBILIER"
echo "============================================================"
echo ""

# ------------------------------------------------------------
# 4. VÉRIFICATION QUE LE DOSSIER TOMCAT EXISTE
# ------------------------------------------------------------

# On définit le chemin du projet (pour ne pas le répéter partout)
PROJECT_DIR="/workspaces/PROGRAMMATION-EN-JAVA-MASTER-SIAD"

# -d = vérifie si le dossier existe
if [ ! -d "$PROJECT_DIR/tomcat9" ]; then
    echo "❌ ERREUR : Tomcat n'est pas installé au bon endroit"
    echo "   Dossier attendu : $PROJECT_DIR/tomcat9"
    echo "   Dossier introuvable !"
    echo ""
    echo "   Action requise :"
    echo "   Vérifie que le dossier tomcat9 existe avec : ls -la $PROJECT_DIR"
    exit 1
fi
echo "✅ Dossier Tomcat trouvé : $PROJECT_DIR/tomcat9"

# ------------------------------------------------------------
# 5. VÉRIFICATION QUE LE SCRIPT DE DÉMARRAGE DE TOMCAT EXISTE
# ------------------------------------------------------------

# -f = vérifie si le fichier existe
if [ ! -f "$PROJECT_DIR/tomcat9/bin/startup.sh" ]; then
    echo "❌ ERREUR : Le script de démarrage de Tomcat est manquant"
    echo "   Fichier attendu : $PROJECT_DIR/tomcat9/bin/startup.sh"
    echo "   Fichier introuvable !"
    echo ""
    echo "   Action requise :"
    echo "   Tomcat est peut-être mal installé"
    echo "   Vérifie avec : ls -la $PROJECT_DIR/tomcat9/bin/"
    exit 1
fi
echo "✅ Script de démarrage Tomcat trouvé"

# ------------------------------------------------------------
# 6. VÉRIFICATION QUE MARIADB (BASE DE DONNÉES) FONCTIONNE
# ------------------------------------------------------------

echo ""
echo "📦 Vérification de la base de données MariaDB..."
echo ""

# On teste la connexion à MariaDB
# On envoie la commande "SELECT 1" qui doit retourner "1"
# "2>/dev/null" = cache les erreurs (on les gère nous-mêmes)
if mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "SELECT 1" > /dev/null 2>&1; then
    echo "✅ MariaDB répond correctement"
else
    echo "⚠️ MariaDB ne répond pas - Tentative de démarrage..."
    
    # On essaie de démarrer MariaDB
    # "2>/dev/null" = cache les erreurs
    if sudo service mariadb start 2>/dev/null; then
        echo "✅ MariaDB démarré avec succès"
        # On attend 2 secondes que MariaDB soit prêt
        sleep 2
    else
        echo "❌ ERREUR : Impossible de démarrer MariaDB"
        echo ""
        echo "   Actions requises :"
        echo "   1. Vérifie si MariaDB est installé : mariadb --version"
        echo "   2. Démarre manuellement : sudo service mariadb start"
        echo "   3. Vérifie l'état : sudo service mariadb status"
        exit 1
    fi
fi

# ------------------------------------------------------------
# 7. VÉRIFICATION DE LA BASE DE DONNÉES "IMMOBILIER"
# ------------------------------------------------------------

echo ""
echo "📁 Vérification de la base de données 'immobilier'..."

# On vérifie si la base "immobilier" existe
# "2>/dev/null" = cache les erreurs
if mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier" 2>/dev/null; then
    echo "✅ Base de données 'immobilier' trouvée"
else
    echo "⚠️ Base de données 'immobilier' manquante - Création en cours..."
    
    # On crée la base de données et les tables
    mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 << 'SQL'
CREATE DATABASE IF NOT EXISTS immobilier;
USE immobilier;
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('admin','utilisateur') DEFAULT 'utilisateur'
);
CREATE TABLE IF NOT EXISTS annonces (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(200) NOT NULL,
    description TEXT,
    prix DECIMAL(10,2) NOT NULL,
    surface INT NOT NULL,
    piece INT NOT NULL,
    adresse VARCHAR(255),
    ville VARCHAR(100),
    code_postal VARCHAR(10),
    type VARCHAR(50),
    utilisateur_id INT NOT NULL
);
SQL
    echo "✅ Base de données créée avec succès"
fi

# ------------------------------------------------------------
# 8. VÉRIFICATION QUE TOMCAT N'EST PAS DÉJÀ EN COURS
# ------------------------------------------------------------

echo ""
echo "🔥 Gestion de Tomcat..."

# pgrep = cherche les processus qui contiennent "catalina" (Tomcat)
# > /dev/null = envoie la sortie vers nulle part (on veut juste le code de retour)
if pgrep -f "catalina" > /dev/null; then
    echo "⚠️ Tomcat semble déjà en cours d'exécution"
    echo "   Arrêt de l'ancienne instance..."
    
    # On arrête Tomcat
    # "2>/dev/null" = cache les erreurs
    $PROJECT_DIR/tomcat9/bin/shutdown.sh 2>/dev/null || true
    # "|| true" = continue même si la commande échoue
    
    # On attend que Tomcat s'arrête complètement
    sleep 3
    echo "✅ Ancienne instance arrêtée"
else
    echo "✅ Aucune instance Tomcat en cours"
fi

# ------------------------------------------------------------
# 9. DÉMARRAGE DE TOMCAT
# ------------------------------------------------------------

echo ""
echo "🚀 Démarrage de Tomcat..."

# On va dans le dossier du projet
cd "$PROJECT_DIR"

# On lance Tomcat
$PROJECT_DIR/tomcat9/bin/startup.sh

echo "⏳ Attente du démarrage de Tomcat (max 10 secondes)..."

# ------------------------------------------------------------
# 10. ATTENTE QUE TOMCAT SOIT PRÊT
# ------------------------------------------------------------

# On va tester jusqu'à 10 fois si Tomcat répond
MAX_TENTATIVES=10
COMPTEUR=0
TOMCAT_OK=false

while [ $COMPTEUR -lt $MAX_TENTATIVES ]; do
    # curl -s = mode silencieux
    # -o /dev/null = jette la réponse
    # -w "%{http_code}" = affiche seulement le code HTTP
    HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080 2>/dev/null || echo "000")
    
    # 200 = OK, 404 = page non trouvée mais Tomcat répond
    if [ "$HTTP_CODE" = "200" ] || [ "$HTTP_CODE" = "404" ]; then
        echo "✅ Tomcat est démarré ! (Code HTTP: $HTTP_CODE)"
        TOMCAT_OK=true
        break
    fi
    
    # On attend 1 seconde avant de réessayer
    sleep 1
    COMPTEUR=$((COMPTEUR + 1))
    echo "   Tentative $COMPTEUR/$MAX_TENTATIVES..."
done

# Si Tomcat n'a pas répondu après 10 tentatives
if [ "$TOMCAT_OK" = false ]; then
    echo ""
    echo "⚠️ ATTENTION : Tomcat ne répond pas après $MAX_TENTATIVES secondes"
    echo ""
    echo "Pour diagnostiquer, exécute ces commandes :"
    echo "  1. Voir les logs : cat $PROJECT_DIR/tomcat9/logs/catalina.out | tail -30"
    echo "  2. Vérifier le port : netstat -tulpn | grep 8080"
    echo "  3. Redémarrer manuellement : $PROJECT_DIR/tomcat9/bin/startup.sh"
    echo ""
    echo "L'application pourrait ne pas être accessible."
fi

# ------------------------------------------------------------
# 11. VÉRIFICATION QUE L'APPLICATION EST DÉPLOYÉE
# ------------------------------------------------------------

echo ""
echo "📦 Vérification du déploiement de l'application..."

# -f = vérifie si le fichier WAR existe
if [ -f "$PROJECT_DIR/tomcat9/webapps/immobilier.war" ]; then
    echo "✅ Application déjà déployée"
else
    echo "⚠️ Application non déployée - Déploiement automatique..."
    
    # On cherche le WAR dans le dossier du projet
    if [ -f "$PROJECT_DIR/immobilier-app/target/immobilier.war" ]; then
        # On copie le WAR dans le dossier webapps de Tomcat
        cp "$PROJECT_DIR/immobilier-app/target/immobilier.war" "$PROJECT_DIR/tomcat9/webapps/"
        echo "✅ Application déployée avec succès"
    else
        echo "❌ ERREUR : Fichier WAR introuvable"
        echo ""
        echo "   Actions requises :"
        echo "   1. Va dans le dossier : cd $PROJECT_DIR/immobilier-app"
        echo "   2. Compile : mvn clean package"
        echo "   3. Relance ce script"
        exit 1
    fi
fi

# ------------------------------------------------------------
# 12. AFFICHAGE DE L'URL POUR ACCÉDER À L'APPLICATION
# ------------------------------------------------------------

echo ""
echo "============================================================"
echo "🌐 APPLICATION PRÊTE !"
echo "============================================================"
echo ""

# On construit l'URL en fonction de l'environnement
# CODESPACE_NAME = variable automatique dans GitHub Codespaces
if [ -n "$CODESPACE_NAME" ]; then
    # On est dans GitHub Codespaces
    DOMAIN="${GITHUB_CODESPACES_PORT_FORWARDING_DOMAIN:-app.github.dev}"
    URL="https://${CODESPACE_NAME}-8080.${DOMAIN}/immobilier/accueil"
    echo "📱 Ouvre cette URL dans ton navigateur :"
    echo ""
    echo "   🔗 $URL"
    echo ""
    echo "   Ou clique sur l'onglet 'Ports' (en bas)"
    echo "   → trouve le port 8080"
    echo "   → clique sur l'icône 🌐"
    echo "   → ajoute '/immobilier/accueil' à la fin"
else
    # On est en local
    echo "📱 Ouvre cette URL dans ton navigateur :"
    echo ""
    echo "   🔗 http://localhost:8080/immobilier/accueil"
fi

echo ""
echo "============================================================"
echo "💡 CONSEILS :"
echo "============================================================"
echo ""
echo "• Si la page ne charge pas, attends 5 secondes et rafraîchis (Ctrl+Shift+R)"
echo "• Si tu vois 'RÃ´le' au lieu de 'Rôle', vide le cache du navigateur"
echo "• Pour te connecter, utilise :"
echo "    Email : hassana@test.com"
echo "    Mot de passe : hassana"
echo "• Pour arrêter Tomcat : ./tomcat9/bin/shutdown.sh"
echo "============================================================"
echo ""

# ------------------------------------------------------------
# 13. FIN DU SCRIPT
# ------------------------------------------------------------

exit 0
