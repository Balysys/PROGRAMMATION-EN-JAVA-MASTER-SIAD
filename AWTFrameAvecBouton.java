import java.awt.*;

public class AWTFrameAvecBouton extends Frame {
    
    public AWTFrameAvecBouton() {
        // Créer un bouton
        Button bt = new Button("Fermer");
        
        // Ajouter le bouton à la fenêtre
        this.add(bt);
        
        // Définir les dimensions
        this.setSize(300, 150);
        
        // Donner un titre
        this.setTitle("Frame AWT avec un bouton");
        
        // Rendre visible
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new AWTFrameAvecBouton();
    }
}
