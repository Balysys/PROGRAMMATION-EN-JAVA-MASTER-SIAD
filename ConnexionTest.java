import java.sql.*;

public class ConnexionTest {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://127.0.0.1:3306/DBHASSANA";
        String user = "hassana_app";
        String password = "Java2026Secure";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("\n✅ SUCCÈS : Connexion établie avec l'utilisateur hassana_app !");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT message FROM test");
            while (rs.next()) {
                System.out.println("📩 Message trouvé : " + rs.getString("message"));
            }
        } catch (SQLException e) {
            System.err.println("\n❌ ERREUR : Impossible de se connecter.");
            e.printStackTrace();
        }
    }
}
