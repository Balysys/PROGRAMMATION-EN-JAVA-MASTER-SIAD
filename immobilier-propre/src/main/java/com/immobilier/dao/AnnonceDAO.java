package com.immobilier.dao;
import com.immobilier.model.Annonce;
import com.immobilier.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AnnonceDAO {
    public boolean ajouter(Annonce a) {
        String sql = "INSERT INTO annonces (titre, description, prix, surface, piece, adresse, ville, code_postal, type, utilisateur_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getTitre()); stmt.setString(2, a.getDescription()); stmt.setBigDecimal(3, a.getPrix());
            stmt.setInt(4, a.getSurface()); stmt.setInt(5, a.getPiece()); stmt.setString(6, a.getAdresse());
            stmt.setString(7, a.getVille()); stmt.setString(8, a.getCodePostal()); stmt.setString(9, a.getType());
            stmt.setInt(10, a.getUtilisateurId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public List<Annonce> toutesLesAnnonces() {
        List<Annonce> list = new ArrayList<>();
        String sql = "SELECT * FROM annonces ORDER BY date_creation DESC";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(extraireAnnonce(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public Annonce trouverParId(int id) {
        String sql = "SELECT * FROM annonces WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extraireAnnonce(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    public boolean modifier(Annonce a) {
        String sql = "UPDATE annonces SET titre=?, description=?, prix=?, surface=?, piece=?, adresse=?, ville=?, code_postal=?, type=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getTitre()); stmt.setString(2, a.getDescription()); stmt.setBigDecimal(3, a.getPrix());
            stmt.setInt(4, a.getSurface()); stmt.setInt(5, a.getPiece()); stmt.setString(6, a.getAdresse());
            stmt.setString(7, a.getVille()); stmt.setString(8, a.getCodePostal()); stmt.setString(9, a.getType());
            stmt.setInt(10, a.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    public boolean supprimer(int id) {
        String sql = "DELETE FROM annonces WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    private Annonce extraireAnnonce(ResultSet rs) throws SQLException {
        Annonce a = new Annonce();
        a.setId(rs.getInt("id")); a.setTitre(rs.getString("titre")); a.setDescription(rs.getString("description"));
        a.setPrix(rs.getBigDecimal("prix")); a.setSurface(rs.getInt("surface")); a.setPiece(rs.getInt("piece"));
        a.setAdresse(rs.getString("adresse")); a.setVille(rs.getString("ville")); a.setCodePostal(rs.getString("code_postal"));
        a.setType(rs.getString("type")); a.setUtilisateurId(rs.getInt("utilisateur_id"));
        Timestamp ts = rs.getTimestamp("date_creation");
        if (ts != null) a.setDateCreation(ts.toLocalDateTime());
        return a;
    }
}
