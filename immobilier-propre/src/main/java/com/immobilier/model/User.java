package com.immobilier.model;
import java.time.LocalDateTime;
public class User {
    private int id; private String nom; private String email; private String motDePasse; private String role; private LocalDateTime dateInscription;
    public User() {}
    public User(String nom, String email, String motDePasse, String role) { this.nom = nom; this.email = email; this.motDePasse = motDePasse; this.role = role; }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; } public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getRole() { return role; } public void setRole(String role) { this.role = role; }
    public LocalDateTime getDateInscription() { return dateInscription; } public void setDateInscription(LocalDateTime dateInscription) { this.dateInscription = dateInscription; }
}
