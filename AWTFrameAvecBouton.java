import java.awt.*; 

public class AWTFrameAvecBouton extends Frame 
{ 
    public AWTFrameAvecBouton() 
    { 
        // Créer une instance de la classe Button avec "Fermer" comme caption 
        Button bt = new Button("Fermer"); 
        
        // ajouter l'instance de Button à l'instance du conteneur Frame 
        this.add(bt); 
        
        // Etablir les dimensions du frame 
        this.setSize(300,150); 
        
        // Donner un titre au frame 
        this.setTitle("Frame AWT avec un bouton"); 
        
        // Rendre le Frame visible 
        this.setVisible(true); 
    } 

    public static void main(String args[]) 
    { 
        // Créer une instance de la classe Frame 
        AWTFrameAvecBouton frameAvecBt = new AWTFrameAvecBouton(); 
    } 
}