// Importation des packages nécessaires
import java.awt.*;           // Pour les composants AWT (Layout, Container)
import javax.swing.*;        // Pour les composants Swing (JFrame, JMenu, etc.)
import java.awt.event.*;     // Pour la gestion des événements (ActionListener, WindowAdapter)

/**
 * Classe principale qui crée une fenêtre Swing avec une barre de menu
 * Elle implémente ActionListener pour réagir aux clics sur les éléments de menu
 */
public class MenuSwing extends JFrame implements ActionListener 
{ 
    // Déclaration des composants de l'interface
    JMenuItem mnAfficher, mnEffacer, mnFermer;  // Éléments du menu
    JTextField tfmsg;                           // Champ de texte pour afficher le message
    JLabel lblmsg;                              // Étiquette "Message"

    /**
     * Point d'entrée principal de l'application
     */
    public static void main(String args[]) 
    { 
        // Créer une instance de MenuSwing (lance la fenêtre)
        MenuSwing fr = new MenuSwing(); 
    } 

    /**
     * Constructeur de la classe - Crée et organise l'interface graphique
     */
    public MenuSwing() 
    { 
        // ========== CRÉATION DE LA BARRE DE MENU ==========
        JMenuBar barreMn = new JMenuBar();        // Barre de menu principale
        JMenu mnFichier = new JMenu("Fichier");   // Menu "Fichier"
        JMenu mnAide = new JMenu("Aide");         // Menu "Aide"
        
        // Création des éléments du menu Fichier
        mnAfficher = new JMenuItem("Afficher");   // Option "Afficher"
        mnEffacer = new JMenuItem("Effacer");     // Option "Effacer"
        mnFermer = new JMenuItem("Fermer");       // Option "Fermer"

        // Définition des raccourcis clavier (Alt+F pour Fichier, Alt+A pour Aide)
        mnFichier.setMnemonic('F'); 
        mnAide.setMnemonic('A'); 

        // Ajout de la barre de menu à la fenêtre
        this.setJMenuBar(barreMn); 

        // Ajout des menus à la barre de menu
        barreMn.add(mnFichier); 
        barreMn.add(mnAide); 

        // Ajout des éléments au menu "Fichier"
        mnFichier.add(mnAfficher); 
        mnFichier.add(mnEffacer); 
        mnFichier.add(mnFermer); 

        // ========== CRÉATION DES COMPOSANTS PRINCIPAUX ==========
        lblmsg = new JLabel("Message");           // Étiquette
        tfmsg = new JTextField(15);               // Champ de texte (15 colonnes)

        // ========== ORGANISATION DU CONTENU ==========
        // Récupération du conteneur principal de la fenêtre
        Container c = this.getContentPane(); 
        c.setLayout(new FlowLayout());            // Disposition en flux (horizontal)
        c.add(lblmsg);                            // Ajout de l'étiquette
        c.add(tfmsg);                             // Ajout du champ texte

        // ========== ENREGISTREMENT DES ÉCOUTEURS ==========
        // La fenêtre elle-même écoute les événements des menus
        mnAfficher.addActionListener(this); 
        mnEffacer.addActionListener(this); 
        mnFermer.addActionListener(this); 

        // ========== CONFIGURATION DE LA FENÊTRE ==========
        this.setSize(320, 150);                   // Dimensions de la fenêtre
        this.setTitle("Menu Swing - Démontration"); // Titre de la fenêtre (ajouté)
        this.setVisible(true);                    // Rendre la fenêtre visible

        // ========== GESTION DE LA FERMETURE DE LA FENÊTRE ==========
        // Création d'une classe anonyme interne pour gérer l'événement de fermeture
        this.addWindowListener(
            new WindowAdapter()  // WindowAdapter fournit une implémentation par défaut
            { 
                /**
                 * Méthode appelée quand on clique sur la croix [X] de la fenêtre
                 */
                public void windowClosing(WindowEvent ev) 
                {
                    fermerFenetre();  // Appelle la méthode de fermeture
                } 
            } 
        ); 
    } 

    /**
     * Méthode obligatoire de l'interface ActionListener
     * Appelée automatiquement quand on clique sur un élément de menu
     * @param e L'événement contenant la source de l'action
     */
    public void actionPerformed(ActionEvent e) 
    { 
        // Vérification quel élément de menu a été cliqué
        if(e.getSource() == mnAfficher) 
        { 
            afficherMessage();   // Affiche "Coucou, Bonjour"
        } 
        if(e.getSource() == mnEffacer) 
        { 
            effacerMessage();    // Efface le texte
        } 
        if(e.getSource() == mnFermer) 
        { 
            fermerFenetre();     // Ferme l'application
        } 
    } 

    /**
     * Affiche le message "Coucou, Bonjour" dans le champ de texte
     */
    public void afficherMessage() 
    { 
        tfmsg.setText("Coucou, Bonjour"); 
    } 

    /**
     * Efface le contenu du champ de texte
     */
    public void effacerMessage() 
    { 
        tfmsg.setText("");   // Texte vide (au lieu d'un espace)
    } 

    /**
     * Ferme proprement la fenêtre et termine l'application
     */
    public void fermerFenetre() 
    { 
        this.dispose();      // Libère les ressources de la fenêtre
        System.exit(0);      // Termine le programme avec code 0 (succès)
    } 
}
