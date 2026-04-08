#!/bin/bash

echo "============================================================"
echo "🏠 GÉNÉRATION DE DONNÉES DE TEST - VERSION SIMPLE"
echo "============================================================"
echo ""

# Nettoyer les anciennes données
echo "⚠️  Nettoyage des anciennes données..."
mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "DROP DATABASE IF EXISTS immobilier; CREATE DATABASE immobilier;" 2>/dev/null

# Recréer les tables
mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 << 'SQL' 2>/dev/null
USE immobilier;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('admin','utilisateur') DEFAULT 'utilisateur'
);

CREATE TABLE annonces (
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

echo "✅ Tables recréées"
echo ""

# ============================================================
# CRÉATION DES UTILISATEURS (sans hash pour le test)
# ============================================================
echo "👥 Création des 10 utilisateurs..."

mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 << 'SQL' 2>/dev/null
USE immobilier;

-- Insérer les utilisateurs (mot de passe en clair pour test)
INSERT INTO users (nom, email, mot_de_passe, role) VALUES
('Hassana Diallo', 'hassana@immobilier.bj', 'hassana', 'utilisateur'),
('Moussa Bio', 'moussa@immobilier.bj', 'moussa123', 'utilisateur'),
('Rachida Saka', 'rachida@immobilier.bj', 'rachida', 'utilisateur'),
('Koffi Amoussou', 'koffi@immobilier.bj', 'koffi123', 'utilisateur'),
('Fatima Garba', 'fatima@immobilier.bj', 'fatima', 'utilisateur'),
('Souleymane Orou', 'souleymane@immobilier.bj', 'souleymane', 'utilisateur'),
('Bénédicte Agossi', 'benedicte@immobilier.bj', 'benedicte', 'utilisateur'),
('Romain Ahouansou', 'romain@immobilier.bj', 'romain123', 'utilisateur'),
('Aïssatou Mama', 'aissatou@immobilier.bj', 'aissatou', 'utilisateur'),
('Jean-Baptiste Dossa', 'admin@immobilier.bj', 'admin123', 'admin');
SQL

echo "✅ 10 utilisateurs créés"
echo ""

# ============================================================
# RÉCUPÉRER LES IDs
# ============================================================
HASSANA_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='hassana@immobilier.bj';" 2>/dev/null | tail -1)
MOUSSA_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='moussa@immobilier.bj';" 2>/dev/null | tail -1)
RACHIDA_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='rachida@immobilier.bj';" 2>/dev/null | tail -1)
KOFFI_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='koffi@immobilier.bj';" 2>/dev/null | tail -1)
FATIMA_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='fatima@immobilier.bj';" 2>/dev/null | tail -1)
SOULEYMANE_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='souleymane@immobilier.bj';" 2>/dev/null | tail -1)
BENEDICTE_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='benedicte@immobilier.bj';" 2>/dev/null | tail -1)
ROMAIN_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='romain@immobilier.bj';" 2>/dev/null | tail -1)
AISSATOU_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='aissatou@immobilier.bj';" 2>/dev/null | tail -1)
ADMIN_ID=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT id FROM users WHERE email='admin@immobilier.bj';" 2>/dev/null | tail -1)

# ============================================================
# GÉNÉRATION DES 100 ANNONCES
# ============================================================
echo "🏠 Génération des 100 annonces immobilières..."

# Villes et quartiers
VILLES=("Cotonou" "Parakou" "Porto-Novo" "Abomey-Calavi" "Bohicon" "Abomey" "Ouidah" "Natitingou")
TYPES=("vente" "location")

TITRES=(
    "Superbe appartement F3" "Villa moderne avec jardin" "Terrain constructible viabilisé"
    "Maison traditionnelle rénovée" "Studio meublé centre ville" "Bureau commercial"
    "Appartement de standing" "Duplex vue mer" "Magasin commercial 100m²"
    "Entrepôt logistique" "Chambre à louer" "Immeuble rapport locatif"
    "Maison avec piscine" "Appartement meublé luxe" "Local commercial"
    "Terrain agricole" "Villa de luxe 5 chambres" "Appartement F4"
    "Maison de ville" "Studio étudiant"
)

DESCRIPTIONS=(
    "Situé dans un quartier calme et résidentiel. Proche des écoles, marchés et transport."
    "Magnifique vue sur la ville. Climatisation réversible, placards intégrés."
    "Idéal pour investissement locatif. Revenus garantis. Sécurité 24/7."
    "Rénové récemment. Peinture neuve, salle de bain moderne."
    "À 5 minutes du centre-ville. Accès facile, parking sécurisé."
    "Parfait pour famille. 3 chambres, salon, salle à manger."
    "Proche université et zone industrielle. Fort potentiel locatif."
    "Terrain viabilisé, prêt à construire. Quartier en expansion."
)

# Tableau des IDs utilisateurs
USER_IDS=($HASSANA_ID $MOUSSA_ID $RACHIDA_ID $KOFFI_ID $FATIMA_ID $SOULEYMANE_ID $BENEDICTE_ID $ROMAIN_ID $AISSATOU_ID $ADMIN_ID)

for i in {1..100}; do
    # Choisir utilisateur aléatoire
    USER_ID=${USER_IDS[$((RANDOM % 10))]}
    
    # Choisir ville
    VILLE=${VILLES[$((RANDOM % ${#VILLES[@]}))]}
    
    # Quartier selon la ville
    case $VILLE in
        "Cotonou") QUARTIERS=("Haie Vive" "Gbegamey" "Fidjrossè" "Akpakpa" "Cadjehoun") ;;
        "Parakou") QUARTIERS=("Camp Goro" "Wansirou" "Banikanni" "Pehunko") ;;
        "Porto-Novo") QUARTIERS=("Adjara" "Avrankou" "Akonaboé" "Dantokpa") ;;
        "Abomey-Calavi") QUARTIERS=("Zogbo" "Gbodjè" "Togba" "Kpanroun") ;;
        *) QUARTIERS=("Centre" "Quartier résidentiel" "Zone commerciale") ;;
    esac
    QUARTIER=${QUARTIERS[$((RANDOM % ${#QUARTIERS[@]}))]}
    ADRESSE="$QUARTIER, $VILLE"
    
    # Type et prix
    TYPE=${TYPES[$((RANDOM % 2))]}
    if [ "$TYPE" = "vente" ]; then
        PRIX=$((15000000 + RANDOM % 85000000))
    else
        PRIX=$((150000 + RANDOM % 350000))
    fi
    
    SURFACE=$((40 + RANDOM % 260))
    PIECES=$((1 + RANDOM % 8))
    TITRE="${TITRES[$((RANDOM % ${#TITRES[@]}))]} - $VILLE"
    DESC="${DESCRIPTIONS[$((RANDOM % ${#DESCRIPTIONS[@]}))]} Surface $SURFACE m², $PIECES pièces."
    
    mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; INSERT INTO annonces (titre, description, prix, surface, piece, adresse, ville, code_postal, type, utilisateur_id) VALUES ('$TITRE', '$DESC', $PRIX, $SURFACE, $PIECES, '$ADRESSE', '$VILLE', '0$((10000 + RANDOM % 90000))', '$TYPE', $USER_ID);" 2>/dev/null
    
    if [ $((i % 10)) -eq 0 ]; then
        echo "   ✅ $i annonces créées..."
    fi
done

echo ""
echo "✅ 100 annonces générées"
echo ""

# ============================================================
# MODIFICATION DE LA SERVLET CONNEXION (pour accepter mot de passe clair)
# ============================================================
echo "🔧 Adaptation de la connexion (mot de passe sans hash)..."

cd /workspaces/PROGRAMMATION-EN-JAVA-MASTER-SIAD/immobilier-app

cat > src/main/java/com/immobilier/servlet/ConnexionServlet.java << 'JAVA'
package com.immobilier.servlet;
import com.immobilier.dao.UserDAO;
import com.immobilier.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String mdp = req.getParameter("motDePasse");
        
        User user = userDAO.trouverParEmail(email);
        
        // Comparaison directe (sans BCrypt pour les données de test)
        if (user != null && mdp.equals(user.getMotDePasse())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/accueil");
        } else {
            req.setAttribute("erreur", "Email ou mot de passe incorrect");
            doGet(req, resp);
        }
    }
}
JAVA

# Recompiler et déployer
mvn clean package -DskipTests > /dev/null 2>&1
cd /workspaces/PROGRAMMATION-EN-JAVA-MASTER-SIAD
rm -rf tomcat9/webapps/immobilier*
cp immobilier-app/target/immobilier.war tomcat9/webapps/
./tomcat9/bin/shutdown.sh 2>/dev/null
sleep 2
./tomcat9/bin/startup.sh
sleep 5

echo ""
echo "============================================================"
echo "📊 RÉCAPITULATIF"
echo "============================================================"

NB_USERS=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT COUNT(*) FROM users;" 2>/dev/null | tail -1)
NB_ANNONCES=$(mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "USE immobilier; SELECT COUNT(*) FROM annonces;" 2>/dev/null | tail -1)

echo "👥 Utilisateurs : $NB_USERS"
echo "🏠 Annonces : $NB_ANNONCES"
echo ""
echo "============================================================"
echo "🔑 IDENTIFIANTS DE CONNEXION (mot de passe en clair)"
echo "============================================================"
echo ""
echo "   hassana@immobilier.bj     → hassana"
echo "   moussa@immobilier.bj      → moussa123"
echo "   rachida@immobilier.bj     → rachida"
echo "   koffi@immobilier.bj       → koffi123"
echo "   fatima@immobilier.bj      → fatima"
echo "   souleymane@immobilier.bj  → souleymane"
echo "   benedicte@immobilier.bj   → benedicte"
echo "   romain@immobilier.bj      → romain123"
echo "   aissatou@immobilier.bj    → aissatou"
echo "   admin@immobilier.bj       → admin123 (ADMIN)"
echo ""
echo "============================================================"
echo "🌍 Villes disponibles : Cotonou, Parakou, Porto-Novo, Abomey-Calavi, Bohicon, Abomey, Ouidah, Natitingou"
echo "============================================================"
echo ""
echo "✅ GÉNÉRATION TERMINÉE !"
echo ""
echo "🌐 Accède à l'application :"
echo "   https://vigilant-goldfish-rjv79wvr9p43x5r-8080.app.github.dev/immobilier/accueil"
echo ""
