package com.immobilier.dao;
import com.immobilier.model.User;
import com.immobilier.util.DBUtil;
import java.sql.*;
public class UserDAO {
    public boolean inscrire(User user) {
        String sql = "INSERT INTO users (nom, email, mot_de_passe, role) VALUES (?,?,?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNom()); stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMotDePasse()); stmt.setString(4, user.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public User trouverParEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id")); u.setNom(rs.getString("nom")); u.setEmail(rs.getString("email"));
                u.setMotDePasse(rs.getString("mot_de_passe")); u.setRole(rs.getString("role"));
                Timestamp ts = rs.getTimestamp("date_inscription");
                if (ts != null) u.setDateInscription(ts.toLocalDateTime());
                return u;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    public User trouverParId(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id")); u.setNom(rs.getString("nom")); u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                Timestamp ts = rs.getTimestamp("date_inscription");
                if (ts != null) u.setDateInscription(ts.toLocalDateTime());
                return u;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
