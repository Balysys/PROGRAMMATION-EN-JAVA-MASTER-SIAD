import java.awt.*;
import java.awt.event.*;

public class FormulaireInscription extends Frame implements ActionListener, WindowListener
{
    // Composants du formulaire
    private Label titre;
    private Label lblNom, lblPrenom, lblEmail, lblTel, lblVille, lblSexe, lblLoisirs;
    private TextField txtNom, txtPrenom, txtEmail, txtTel;
    private Choice choixVille;
    private CheckboxGroup sexeGroup;
    private Checkbox sexeHomme, sexeFemme;
    private Checkbox loisirSport, loisirMusique, loisirLecture, loisirVoyage;
    private Button btnValider, btnEffacer, btnFermer;
    private TextArea zoneAffichage;
    private Panel panneauPrincipal, panneauFormulaire, panneauBoutons, panneauAffichage;
    
    public FormulaireInscription()
    {
        // Configuration de la fenêtre principale
        setTitle("FORMULAIRE D'INSCRIPTION");
        setSize(850, 550);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));
        
        // Création du titre
        titre = new Label("FORMULAIRE D'INSCRIPTION", Label.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setForeground(new Color(0, 51, 102));
        titre.setBackground(new Color(173, 216, 230));
        
        // ========== PANEAU FORMULAIRE (Partie gauche) ==========
        panneauFormulaire = new Panel(new GridLayout(9, 2, 10, 10));
        panneauFormulaire.setBackground(new Color(240, 248, 255));
        
        // Ligne 1 : Nom
        lblNom = new Label("Nom :");
        lblNom.setFont(new Font("Arial", Font.BOLD, 12));
        txtNom = new TextField(20);
        
        // Ligne 2 : Prénom
        lblPrenom = new Label("Prénom :");
        lblPrenom.setFont(new Font("Arial", Font.BOLD, 12));
        txtPrenom = new TextField(20);
        
        // Ligne 3 : Email
        lblEmail = new Label("Email :");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        txtEmail = new TextField(20);
        
        // Ligne 4 : Téléphone
        lblTel = new Label("Téléphone :");
        lblTel.setFont(new Font("Arial", Font.BOLD, 12));
        txtTel = new TextField(20);
        
        // Ligne 5 : Sexe
        lblSexe = new Label("Sexe :");
        lblSexe.setFont(new Font("Arial", Font.BOLD, 12));
        sexeGroup = new CheckboxGroup();
        sexeHomme = new Checkbox("Homme", sexeGroup, true);
        sexeFemme = new Checkbox("Femme", sexeGroup, false);
        Panel sexePanel = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sexePanel.add(sexeHomme);
        sexePanel.add(sexeFemme);
        
        // Ligne 6 : Ville
        lblVille = new Label("Ville :");
        lblVille.setFont(new Font("Arial", Font.BOLD, 12));
        choixVille = new Choice();
        choixVille.add("Sélectionnez une ville");
        choixVille.add("Casablanca");
        choixVille.add("Rabat");
        choixVille.add("Marrakech");
        choixVille.add("Fès");
        choixVille.add("Tanger");
        choixVille.add("Agadir");
        
        // Ligne 7-8 : Loisirs
        lblLoisirs = new Label("Loisirs :");
        lblLoisirs.setFont(new Font("Arial", Font.BOLD, 12));
        loisirSport = new Checkbox("Sport");
        loisirMusique = new Checkbox("Musique");
        loisirLecture = new Checkbox("Lecture");
        loisirVoyage = new Checkbox("Voyage");
        Panel loisirPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        loisirPanel.add(loisirSport);
        loisirPanel.add(loisirMusique);
        loisirPanel.add(loisirLecture);
        loisirPanel.add(loisirVoyage);
        
        // Ajout des composants au panneau formulaire
        panneauFormulaire.add(lblNom);
        panneauFormulaire.add(txtNom);
        panneauFormulaire.add(lblPrenom);
        panneauFormulaire.add(txtPrenom);
        panneauFormulaire.add(lblEmail);
        panneauFormulaire.add(txtEmail);
        panneauFormulaire.add(lblTel);
        panneauFormulaire.add(txtTel);
        panneauFormulaire.add(lblSexe);
        panneauFormulaire.add(sexePanel);
        panneauFormulaire.add(lblVille);
        panneauFormulaire.add(choixVille);
        panneauFormulaire.add(lblLoisirs);
        panneauFormulaire.add(loisirPanel);
        
        // ========== PANEAU AFFICHAGE (Partie droite) ==========
        panneauAffichage = new Panel(new BorderLayout());
        panneauAffichage.setBackground(new Color(255, 255, 224));
        
        Label lblResultat = new Label("INFORMATIONS SAISIES", Label.CENTER);
        lblResultat.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultat.setForeground(new Color(0, 100, 0));
        
        zoneAffichage = new TextArea("", 15, 35, TextArea.SCROLLBARS_VERTICAL_ONLY);
        zoneAffichage.setEditable(false);
        zoneAffichage.setFont(new Font("Monospaced", Font.PLAIN, 12));
        zoneAffichage.setBackground(new Color(255, 255, 224));
        
        panneauAffichage.add(lblResultat, BorderLayout.NORTH);
        panneauAffichage.add(zoneAffichage, BorderLayout.CENTER);
        
        // ========== PANEAU BOUTONS (En bas) ==========
        panneauBoutons = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panneauBoutons.setBackground(new Color(220, 220, 220));
        
        btnValider = new Button("VALIDER");
        btnValider.setBackground(new Color(0, 153, 0));
        btnValider.setForeground(Color.WHITE);
        btnValider.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnEffacer = new Button("EFFACER");
        btnEffacer.setBackground(new Color(255, 102, 0));
        btnEffacer.setForeground(Color.WHITE);
        btnEffacer.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnFermer = new Button("FERMER");
        btnFermer.setBackground(new Color(204, 0, 0));
        btnFermer.setForeground(Color.WHITE);
        btnFermer.setFont(new Font("Arial", Font.BOLD, 12));
        
        panneauBoutons.add(btnValider);
        panneauBoutons.add(btnEffacer);
        panneauBoutons.add(btnFermer);
        
        // ========== PANEAU PRINCIPAL ==========
        panneauPrincipal = new Panel(new GridLayout(1, 2, 15, 10));
        panneauPrincipal.setBackground(new Color(240, 248, 255));
        panneauPrincipal.add(panneauFormulaire);
        panneauPrincipal.add(panneauAffichage);
        
        // Ajout de tout à la fenêtre
        add(titre, BorderLayout.NORTH);
        add(panneauPrincipal, BorderLayout.CENTER);
        add(panneauBoutons, BorderLayout.SOUTH);
        
        // ========== AJOUT DES ÉCOUTEURS ==========
        btnValider.addActionListener(this);
        btnEffacer.addActionListener(this);
        btnFermer.addActionListener(this);
        addWindowListener(this);
        
        // Centrer la fenêtre
        setLocationRelativeTo(null);
        
        // Afficher la fenêtre
        setVisible(true);
    }
    
    // ========== MÉTHODE POUR AFFICHER LES INFORMATIONS ==========
    private void afficherInformations()
    {
        // Vérifier que les champs obligatoires sont remplis
        if(txtNom.getText().trim().isEmpty() || txtPrenom.getText().trim().isEmpty())
        {
            zoneAffichage.setText("ERREUR : Veuillez saisir au moins le Nom et le Prénom !");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════╗\n");
        sb.append("║       INFORMATIONS PERSONNELLES        ║\n");
        sb.append("╚════════════════════════════════════════╝\n\n");
        
        sb.append(" Nom complet : ").append(txtNom.getText().toUpperCase())
          .append(" ").append(txtPrenom.getText()).append("\n\n");
        
        sb.append(" Email       : ").append(txtEmail.getText()).append("\n");
        sb.append(" Téléphone   : ").append(txtTel.getText()).append("\n");
        
        // Sexe
        if(sexeHomme.getState())
            sb.append(" Sexe        : Homme\n");
        else
            sb.append(" Sexe        : Femme\n");
        
        // Ville
        String ville = choixVille.getSelectedItem();
        if(ville.equals("Sélectionnez une ville"))
            ville = "Non spécifiée";
        sb.append(" Ville       : ").append(ville).append("\n");
        
        // Loisirs
        sb.append(" Loisirs     : ");
        int count = 0;
        if(loisirSport.getState()) { sb.append("Sport "); count++; }
        if(loisirMusique.getState()) { sb.append("Musique "); count++; }
        if(loisirLecture.getState()) { sb.append("Lecture "); count++; }
        if(loisirVoyage.getState()) { sb.append("Voyage "); count++; }
        if(count == 0) sb.append("Aucun");
        
        zoneAffichage.setText(sb.toString());
    }
    
    // ========== MÉTHODE POUR EFFACER LE FORMULAIRE ==========
    private void effacerFormulaire()
    {
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTel.setText("");
        sexeGroup.setSelectedCheckbox(sexeHomme);
        choixVille.select(0);
        loisirSport.setState(false);
        loisirMusique.setState(false);
        loisirLecture.setState(false);
        loisirVoyage.setState(false);
        zoneAffichage.setText("");
        txtNom.requestFocus();
    }
    
    // ========== MÉTHODE POUR FERMER ==========
    private void fermer()
    {
        dispose();
        System.exit(0);
    }
    
    // ========== GESTION DES ÉVÉNEMENTS ==========
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnValider)
        {
            afficherInformations();
        }
        else if(e.getSource() == btnEffacer)
        {
            effacerFormulaire();
        }
        else if(e.getSource() == btnFermer)
        {
            fermer();
        }
    }
    
    // ========== GESTION DE LA FENÊTRE ==========
    public void windowClosing(WindowEvent e) { fermer(); }
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    
    // ========== MAIN ==========
    public static void main(String[] args)
    {
        // Pour GitHub Codespaces
        if(GraphicsEnvironment.isHeadless())
        {
            System.setProperty("java.awt.headless", "false");
        }
        if(System.getenv("DISPLAY") == null || System.getenv("DISPLAY").isEmpty())
        {
            System.setProperty("DISPLAY", ":99");
        }
        
        new FormulaireInscription();
    }
}