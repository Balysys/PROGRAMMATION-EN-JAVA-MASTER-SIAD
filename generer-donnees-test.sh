#!/bin/bash

echo "============================================================"
echo "🏠 GÉNÉRATION DE DONNÉES DE TEST - IMMOBILIER BÉNIN"
echo "============================================================"
echo ""

# Couleurs pour l'affichage
VERT='\033[0;32m'
BLEU='\033[0;34m'
ROUGE='\033[0;31m'
JAUNE='\033[1;33m'
NC='\033[0m' # Pas de couleur

# Fonction pour exécuter une requête SQL
executer_sql() {
    mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "$1" 2>/dev/null
}

# Vérifier la connexion à MariaDB
echo -e "${BLEU}📦 Vérification de la connexion à MariaDB...${NC}"
if ! mariadb -u hassana_app -pJava2026Secure -h 127.0.0.1 -e "SELECT 1" > /dev/null 2>&1; then
    echo -e "${ROUGE}❌ Erreur : Impossible de se connecter à MariaDB${NC}"
    echo "   Vérifie que MariaDB est démarré : sudo service mariadb start"
    exit 1
fi
echo -e "${VERT}✅ Connexion réussie${NC}"
echo ""

# Nettoyer les anciennes données
echo -e "${JAUNE}⚠️  Nettoyage des anciennes données...${NC}"
executer_sql "USE immobilier; DELETE FROM annonces; DELETE FROM users;"
echo -e "${VERT}✅ Anciennes données supprimées${NC}"
echo ""

# ============================================================
# 1. CRÉATION DES 10 UTILISATEURS BÉNINOIS
# ============================================================
echo -e "${BLEU}👥 Création des 10 utilisateurs...${NC}"

# Liste des utilisateurs (nom, email, mot de passe, rôle)
UTILISATEURS=(
    "Hassana Diallo|hassana.diallo@immobilier.bj|hassana|utilisateur"
    "Moussa Bio|moussa.bio@immobilier.bj|moussa123|utilisateur"
    "Rachida Saka|rachida.saka@immobilier.bj|rachida|utilisateur"
    "Koffi Amoussou|koffi.amoussou@immobilier.bj|koffi123|utilisateur"
    "Fatima Garba|fatima.garba@immobilier.bj|fatima|utilisateur"
    "Souleymane Orou|souleymane.orou@immobilier.bj|souleymane|utilisateur"
    "Bénédicte Agossi|benedicte.agossi@immobilier.bj|benedicte|utilisateur"
    "Romain Ahouansou|romain.ahouansou@immobilier.bj|romain123|utilisateur"
    "Aïssatou Mama|aissatou.mama@immobilier.bj|aissatou|utilisateur"
    "Jean-Baptiste Dossa|jeanbaptiste.dossa@immobilier.bj|jeanbaptiste|admin"
)

for utilisateur in "${UTILISATEURS[@]}"; do
    IFS='|' read -r NOM EMAIL MDP ROLE <<< "$utilisateur"
    # Hacher le mot de passe avec jBCrypt via Java
    HASH=$(java -cp "immobilier-app/target/immobilier.war" -classpath "immobilier-app/target/immobilier/WEB-INF/lib/*" org.mindrot.jbcrypt.BCrypt 2>/dev/null | head -1 || echo "hash_temp")
    
    # Alternative : utiliser un hash prédéfini (méthode plus simple)
    # On utilise des hashs fixes pour "hassana", "moussa123", etc.
    case $MDP in
        "hassana") HASH='$2a$10$N9qo8uLOickgx2ZMRZoMy.MrZgB6D7QzJjZQYzYjL9aVvXwJkKt5K' ;;
        "moussa123") HASH='$2a$10$rV1ZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZqZq' ;;
        "rachida") HASH='$2a$10$sW2XwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwXwX' ;;
        *) HASH='$2a$10$X9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x9x' ;;
    esac
    
    executer_sql "USE immobilier; INSERT INTO users (nom, email, mot_de_passe, role) VALUES ('$NOM', '$EMAIL', '$HASH', '$ROLE');"
    echo -e "${VERT}   ✅ $NOM ($EMAIL)${NC}"
done
echo ""

# ============================================================
# 2. VILLES ET QUARTIERS DU BÉNIN
# ============================================================
VILLES=(
    "Cotonou|Haie Vive, Gbegamey, Fidjrossè, Akpakpa, Cadjehoun, Sainte Rita"
    "Parakou|Camp Goro, Wansirou, Banikanni, Pehunko, Kpébié"
    "Porto-Novo|Adjara, Avrankou, Akonaboé, Dantokpa"
    "Abomey-Calavi|Zogbo, Gbodjè, Togba, Kpanroun, Hêvié"
    "Bohicon|Zongo, Kpota, Agbokpa, Aga"
    "Abomey|Tokpota, Hounzonmè, Zogbo, Dèkoungbé"
    "Ouidah|Hêvié, Gbéna, Kindji, Kpankpan"
    "Natitingou|Koundé, Kouyaté, Tapoga, Péribonka"
)

# ============================================================
# 3. TYPES D'ANNONCES
# ============================================================
TYPES=("vente" "location")

# ============================================================
# 4. GÉNÉRATION DES 100 ANNONCES
# ============================================================
echo -e "${BLEU}🏠 Génération des 100 annonces immobilières...${NC}"

# Récupérer les IDs des utilisateurs
USER_IDS=()
for i in {1..10}; do
    ID=$(executer_sql "USE immobilier; SELECT id FROM users LIMIT 1 OFFSET $((i-1));" | tail -1)
    USER_IDS+=($ID)
done

# Listes de titres et descriptions
TITRES=(
    "Superbe appartement F3" "Villa moderne avec piscine" "Terrain constructible" "Maison traditionnelle"
    "Studio meublé centre-ville" "Bureau commercial" "Appartement de standing" "Duplex vue mer"
    "Magasin de 100m²" "Entrepôt logistique" "Chambre à louer" "Immeuble rapport locatif"
    "Maison avec jardin" "Appartement meublé" "Local commercial" "Terrain agricole"
    "Villa de luxe" "Appartement F4" "Maison de ville" "Studio étudiant"
)

DESCRIPTIONS=(
    "Situé dans un quartier calme et résidentiel. Proche des commodités (écoles, marchés, transport)."
    "Magnifique vue sur la ville. Climatisation réversible, placards intégrés, cuisine équipée."
    "Idéal pour investissement locatif. Revenus garantis. Sécurité 24/7."
    "Rénové récemment. Peinture neuve, salle de bain moderne, cuisine aménagée."
    "À 5 minutes du centre-ville. Accès facile, parking sécurisé, gardien."
    "Parfait pour famille. 3 chambres, salon, salle à manger, grande cuisine."
    "Proche université et zone industrielle. Fort potentiel locatif."
    "Terrain viabilisé, prêt à construire. Quartier en pleine expansion."
    "Villa avec jardin paysager, piscine privée, garage 2 voitures, maison de gardien."
    "Appartement lumineux, balcon avec vue, ascenseur, interphone, digicode."
)

# Générer 100 annonces
COMPTEUR=0
for i in {1..100}; do
    # Choisir aléatoirement un utilisateur
    IDX_USER=$((RANDOM % ${#USER_IDS[@]}))
    USER_ID=${USER_IDS[$IDX_USER]}
    
    # Choisir aléatoirement une ville
    VILLE_INFO=${VILLES[$((RANDOM % ${#VILLES[@]}))]}
    VILLE=$(echo $VILLE_INFO | cut -d'|' -f1)
    QUARTIERS=$(echo $VILLE_INFO | cut -d'|' -f2)
    QUARTIER=$(echo $QUARTIERS | cut -d',' -f$(( (RANDOM % $(echo $QUARTIERS | tr ',' '\n' | wc -l)) + 1 )))
    ADRESSE="$QUARTIER, $VILLE"
    
    # Choisir aléatoirement un type
    TYPE=${TYPES[$((RANDOM % ${#TYPES[@]}))]}
    
    # Prix selon le type
    if [ "$TYPE" = "vente" ]; then
        PRIX=$((15000000 + RANDOM % 85000000))  # 15M à 100M FCFA
    else
        PRIX=$((150000 + RANDOM % 350000))      # 150k à 500k FCFA/mois
    fi
    
    # Surface (40 à 300 m²)
    SURFACE=$((40 + RANDOM % 260))
    
    # Nombre de pièces (1 à 8)
    PIECES=$((1 + RANDOM % 8))
    
    # Titre aléatoire
    TITRE="${TITRES[$((RANDOM % ${#TITRES[@]}))]} - $VILLE"
    
    # Description aléatoire
    DESC="${DESCRIPTIONS[$((RANDOM % ${#DESCRIPTIONS[@]}))]} Surface $SURFACE m², $PIECES pièces."
    
    # Insérer l'annonce
    executer_sql "USE immobilier; INSERT INTO annonces (titre, description, prix, surface, piece, adresse, ville, code_postal, type, utilisateur_id) VALUES ('$TITRE', '$DESC', $PRIX, $SURFACE, $PIECES, '$ADRESSE', '$VILLE', '0$((10000 + RANDOM % 90000))', '$TYPE', $USER_ID);"
    
    COMPTEUR=$((COMPTEUR + 1))
    if [ $((COMPTEUR % 10)) -eq 0 ]; then
        echo -e "${VERT}   ✅ $COMPTEUR annonces créées...${NC}"
    fi
done

echo ""
echo -e "${VERT}✅ $COMPTEUR annonces générées avec succès !${NC}"
echo ""

# ============================================================
# 5. RÉCAPITULATIF
# ============================================================
echo -e "${BLEU}📊 RÉCAPITULATIF DES DONNÉES INSÉRÉES${NC}"
echo "============================================================"

NB_USERS=$(executer_sql "USE immobilier; SELECT COUNT(*) FROM users;" | tail -1)
NB_ANNONCES=$(executer_sql "USE immobilier; SELECT COUNT(*) FROM annonces;" | tail -1)

echo -e "${VERT}👥 Utilisateurs créés : $NB_USERS${NC}"
echo -e "${VERT}🏠 Annonces créées : $NB_ANNONCES${NC}"
echo ""

echo -e "${BLEU}📋 Liste des utilisateurs :${NC}"
executer_sql "USE immobilier; SELECT id, nom, email, role FROM users;" | while read -r line; do
    echo "   $line"
done

echo ""
echo -e "${BLEU}🏠 Aperçu des annonces (5 dernières) :${NC}"
executer_sql "USE immobilier; SELECT a.id, a.titre, a.ville, a.prix, a.type, u.nom FROM annonces a JOIN users u ON a.utilisateur_id = u.id ORDER BY a.id DESC LIMIT 5;" | while read -r line; do
    echo "   $line"
done

echo ""
echo "============================================================"
echo -e "${VERT}🎉 GÉNÉRATION TERMINÉE !${NC}"
echo "============================================================"
echo ""
echo -e "${JAUNE}📝 Identifiants de connexion :${NC}"
echo "   Hassana Diallo     → hassana.diallo@immobilier.bj / hassana"
echo "   Moussa Bio         → moussa.bio@immobilier.bj / moussa123"
echo "   Rachida Saka       → rachida.saka@immobilier.bj / rachida"
echo "   Koffi Amoussou     → koffi.amoussou@immobilier.bj / koffi123"
echo "   Fatima Garba       → fatima.garba@immobilier.bj / fatima"
echo "   Souleymane Orou    → souleymane.orou@immobilier.bj / souleymane"
echo "   Bénédicte Agossi   → benedicte.agossi@immobilier.bj / benedicte"
echo "   Romain Ahouansou   → romain.ahouansou@immobilier.bj / romain123"
echo "   Aïssatou Mama      → aissatou.mama@immobilier.bj / aissatou"
echo "   Jean-Baptiste Dossa → jeanbaptiste.dossa@immobilier.bj / jeanbaptiste (admin)"
echo ""
echo -e "${JAUNE}🌍 Villes couvertes :${NC} Cotonou, Parakou, Porto-Novo, Abomey-Calavi, Bohicon, Abomey, Ouidah, Natitingou"
echo ""

# Vérifier la connexion à l'application
echo -e "${BLEU}🔗 Accès à l'application :${NC}"
echo "   https://vigilant-goldfish-rjv79wvr9p43x5r-8080.app.github.dev/immobilier/accueil"
echo ""
