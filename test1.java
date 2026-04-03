import java.awt.*;
import java.awt.event.*;

public class test1 extends Frame implements ActionListener {
    
    Button btAfficher, btEffacer, btFermer;
    TextField tfmsg;
    Label lblmsg;
    
    // CORRIGÉ : Le constructeur doit avoir le même nom que la classe
    public test1() {
        // Créer les composants
        btAfficher = new Button("Afficher");
        btEffacer = new Button("Effacer");
        btFermer = new Button("Fermer");
        lblmsg = new Label("Message");
        tfmsg = new TextField(15);
        
        // Créer les panneaux
        Panel pbas = new Panel();
        Panel phaut = new Panel();
        
        // Ajouter au panneau du haut
        phaut.add(lblmsg);
        phaut.add(tfmsg);
        
        // Ajouter au panneau du bas
        pbas.add(btAfficher);
        pbas.add(btEffacer);
        pbas.add(btFermer);
        
        // Ajouter les panneaux à la fenêtre
        this.add("North", phaut);
        this.add("South", pbas);
        
        // Enregistrer les écouteurs
        btAfficher.addActionListener(this);
        btEffacer.addActionListener(this);
        btFermer.addActionListener(this);
        
        // Gestion de la fermeture
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                fermerFenetre();
            }
        });
        
        this.setSize(320, 150);
        this.setTitle("Plusieurs contrôles AWT");
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btAfficher) {
            afficherMessage();
        } else if(e.getSource() == btEffacer) {
            effacerMessage();
        } else if(e.getSource() == btFermer) {
            fermerFenetre();
        }
    }
    
    public void afficherMessage() {
        tfmsg.setText("Coucou, Bonjour");
    }
    
    public void effacerMessage() {
        tfmsg.setText("");
    }
    
    public void fermerFenetre() {
        this.dispose();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new test1();
    }
}