package com.immobilier.model;
import java.math.BigDecimal;

public class Annonce {
    private int id;
    private String titre;
    private String description;
    private BigDecimal prix;
    private int surface;
    private int piece;
    private String adresse;
    private String ville;
    private String codePostal;
    private String type;
    private int utilisateurId;
    private String proprietaireNom;  // NOUVEAU : nom de la personne qui a posté
    
    public Annonce() {}
    
    public Annonce(String titre, String description, BigDecimal prix, int surface, int piece, String adresse, String ville, String codePostal, String type, int utilisateurId) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.surface = surface;
        this.piece = piece;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.type = type;
        this.utilisateurId = utilisateurId;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    
    public int getSurface() { return surface; }
    public void setSurface(int surface) { this.surface = surface; }
    
    public int getPiece() { return piece; }
    public void setPiece(int piece) { this.piece = piece; }
    
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    
    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
    
    public String getProprietaireNom() { return proprietaireNom; }
    public void setProprietaireNom(String proprietaireNom) { this.proprietaireNom = proprietaireNom; }
}
