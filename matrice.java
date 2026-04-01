import java.util.Scanner; // Importe la classe Scanner pour pouvoir lire les entrées utilisateur au clavier

public class matrice { 
    
    public static void main(String[] args) { // Déclare la méthode main, point d'entrée du programme
        // public : accessible depuis n'importe où
        // static : peut être appelée sans créer d'objet
        // void : ne retourne aucune valeur
        // String[] args : paramètre pour recevoir les arguments de la ligne de commande
        
        Scanner scanner = new Scanner(System.in); // Crée un objet Scanner pour lire les données tapées au clavier (System.in = entrée standard)
        
        // Demander la taille des matrices carrées
        System.out.print("Entrez la taille des matrices (n) : "); // Affiche un message à l'utilisateur sans passer à la ligne
        int n = scanner.nextInt(); // Lit un nombre entier tapé par l'utilisateur et le stocke dans la variable n (taille des matrices)
        
        // Déclaration des matrices
        int[][] A = new int[n][n]; // Crée une matrice A à 2 dimensions avec n lignes et n colonnes, contenant des entiers
        int[][] B = new int[n][n]; // Crée une matrice B à 2 dimensions avec n lignes et n colonnes, contenant des entiers
        int[][] C = new int[n][n]; // Crée une matrice C à 2 dimensions avec n lignes et n colonnes, contenant des entiers (pour le résultat)
        
        // Lecture de la première matrice
        System.out.println("Entrez les éléments de la première matrice :"); // Affiche un message pour inviter l'utilisateur à saisir la première matrice
        for (int i = 0; i < n; i++) { // Boucle extérieure : parcourt les lignes (i = indice de la ligne, de 0 à n-1)
            for (int j = 0; j < n; j++) { // Boucle intérieure : parcourt les colonnes (j = indice de la colonne, de 0 à n-1)
                System.out.print("A[" + i + "][" + j + "] = "); // Affiche l'emplacement de l'élément à saisir (ex: A[0][0] = )
                A[i][j] = scanner.nextInt(); // Lit la valeur entrée par l'utilisateur et la stocke dans la matrice A à la position [i][j]
            } // Fin de la boucle sur les colonnes
        } // Fin de la boucle sur les lignes
        
        // Lecture de la deuxième matrice
        System.out.println("Entrez les éléments de la deuxième matrice :"); // Affiche un message pour inviter l'utilisateur à saisir la deuxième matrice
        for (int i = 0; i < n; i++) { // Boucle extérieure : parcourt les lignes (i = indice de la ligne, de 0 à n-1)
            for (int j = 0; j < n; j++) { // Boucle intérieure : parcourt les colonnes (j = indice de la colonne, de 0 à n-1)
                System.out.print("B[" + i + "][" + j + "] = "); // Affiche l'emplacement de l'élément à saisir (ex: B[0][0] = )
                B[i][j] = scanner.nextInt(); // Lit la valeur entrée par l'utilisateur et la stocke dans la matrice B à la position [i][j]
            } // Fin de la boucle sur les colonnes
        } // Fin de la boucle sur les lignes
        
        // Calcul du produit C = A * B
        for (int i = 0; i < n; i++) { // Boucle sur les lignes de la matrice résultat C (et aussi de A)
            for (int j = 0; j < n; j++) { // Boucle sur les colonnes de la matrice résultat C (et aussi de B)
                C[i][j] = 0; // Initialise l'élément C[i][j] à 0 avant d'accumuler les produits
                for (int k = 0; k < n; k++) { // Boucle de sommation : k parcourt les colonnes de A et les lignes de B
                    C[i][j] += A[i][k] * B[k][j]; // Calcule la somme : C[i][j] = C[i][j] + (A[i][k] × B[k][j])
                    // += est un opérateur qui ajoute la valeur de droite à la variable de gauche
                    // A[i][k] : élément de la ligne i de A, colonne k
                    // B[k][j] : élément de la ligne k de B, colonne j
                } // Fin de la boucle de sommation
            } // Fin de la boucle sur les colonnes
        } // Fin de la boucle sur les lignes
        
        // Affichage du résultat
        System.out.println("\nProduit des deux matrices :"); // Affiche un titre avec un saut de ligne (\n)
        for (int i = 0; i < n; i++) { // Boucle sur les lignes de la matrice résultat
            for (int j = 0; j < n; j++) { // Boucle sur les colonnes de la matrice résultat
                System.out.print(C[i][j] + "\t"); // Affiche l'élément C[i][j] suivi d'une tabulation (\t) pour aligner les colonnes
            } // Fin de la boucle sur les colonnes
            System.out.println(); // Après chaque ligne, passe à la ligne suivante (retour à la ligne)
        } // Fin de la boucle sur les lignes
        
        scanner.close(); // Ferme l'objet Scanner pour libérer les ressources système (important pour éviter les fuites mémoire)
    } // Fin de la méthode main
} // Fin de la classe ProduitMatrices
