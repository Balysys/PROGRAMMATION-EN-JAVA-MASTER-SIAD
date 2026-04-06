import java.sql.*;
import java.util.*;

public class TestComplet {
    public static void main(String[] args) {
        System.out.println("\n==================================================");
        System.out.println("✅ TEST DE CONNEXION JAVA + MARIADB");
        System.out.println("==================================================\n");
        
        String url = "jdbc:mariadb://localhost:3306/DBHASSANA?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "";
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("🎉 CONNEXION RÉUSSIE !\n");
            
            // Afficher les utilisateurs
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateurs");
            System.out.println("👥 LISTE DES UTILISATEURS:");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("ID: %d | Nom: %-20s | Email: %-25s | Âge: %d%n",
                    rs.getInt("id"), rs.getString("nom"), 
                    rs.getString("email"), rs.getInt("age"));
            }
            
            // Afficher les produits
            rs = stmt.executeQuery("SELECT * FROM produits");
            System.out.println("\n📦 LISTE DES PRODUITS:");
            System.out.println("----------------------------------------");
            while (rs.next()) {
                System.out.printf("ID: %d | %-25s | Prix: %8.2f€ | Stock: %d%n",
                    rs.getInt("id"), rs.getString("nom_produit"),
                    rs.getDouble("prix"), rs.getInt("stock"));
            }
            
            // Statistiques
            rs = stmt.executeQuery("SELECT COUNT(*) as total, AVG(prix) as prix_moyen FROM produits");
            if (rs.next()) {
                System.out.println("\n📊 STATISTIQUES:");
                System.out.println("----------------------------------------");
                System.out.printf("Total produits: %d%n", rs.getInt("total"));
                System.out.printf("Prix moyen: %.2f€%n", rs.getDouble("prix_moyen"));
            }
            
            conn.close();
            System.out.println("\n✅ TEST COMPLET RÉUSSI !\n");
            
        } catch (Exception e) {
            System.err.println("❌ ERREUR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
