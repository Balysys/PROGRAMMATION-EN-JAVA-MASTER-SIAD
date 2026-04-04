import java.awt.*;
import java.awt.event.*;

public class 

 extends Frame implements WindowListener, ActionListener 
{ 
    private Button bt;
    
    public AWTFrameAvecBouton1() 
    { 
        this.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        
        // Créer une instance de la classe Button avec "Fermer" comme caption 
        bt = new Button("Fermer"); 
        bt.addActionListener(this);  // Ajout de l'écouteur sur le bouton
        
        // ajouter l'instance de Button à l'instance du conteneur Frame 
        this.add(bt); 
        
        // Etablir les dimensions du frame 
        this.setSize(300,150); 
        
        // Donner un titre au frame 
        this.setTitle("Frame AWT avec un bouton"); 
        
        // Rendre le Frame visible 
        this.setVisible(true); 
        
        // Enregistrer l'instance de frame comme son propre écouteur 
        this.addWindowListener(this); 
    } 
    
    public static void main(String args[]) 
    { 
        AWTFrameAvecBouton1 frameAvecBt = new AWTFrameAvecBouton1(); 
    } 
    
    // ActionListener pour le bouton
    public void actionPerformed(ActionEvent e) 
    { 
        fermerFenetre(); 
    } 
    
    // WindowListener
    public void windowClosing(WindowEvent ev) 
    { 
        fermerFenetre(); 
    } 
    
    public void windowClosed(WindowEvent ev) {} 
    public void windowDeiconified(WindowEvent ev){} 
    public void windowIconified(WindowEvent ev){} 
    public void windowActivated(WindowEvent ev){} 
    public void windowDeactivated(WindowEvent ev){} 
    public void windowOpened(WindowEvent ev){} 
    
    public void fermerFenetre() 
    { 
        this.dispose(); 
        System.exit(0); 
    } 
}