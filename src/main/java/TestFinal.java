import java.sql.*;

public class TestFinal {
    public static void main(String[] args) {
        // Tentative de connexion avec plusieurs stratégies
        String[] urls = {
            "jdbc:mariadb://localhost:3306/DBHASSANA?useSSL=false&allowPublicKeyRetrieval=true",
            "jdbc:mariadb://127.0.0.1:3306/DBHASSANA?useSSL=false",
            "jdbc:mariadb:///DBHASSANA?host=localhost&port=3306"
        };
        
        String user = "root";
        String password = "";
        
        for (int i = 0; i < urls.length; i++) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                System.out.println("Tentative " + (i+1) + "...");
                Connection conn = DriverManager.getConnection(urls[i], user, password);
                System.out.println("✅ Connecté !\n");
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM produits");
                System.out.println("Produits disponibles:");
                while (rs.next()) {
                    System.out.println("  - " + rs.getString("nom_produit") + 
                                     " | " + rs.getDouble("prix") + "€");
                }
                conn.close();
                return;
            } catch (Exception e) {
                System.out.println("  Échec: " + e.getMessage());
            }
        }
        System.out.println("\n❌ Toutes les tentatives ont échoué");
    }
}