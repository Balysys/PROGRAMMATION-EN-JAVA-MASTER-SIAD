import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestGUI {
    
    private JFrame frame;
    private JTextField tfmsg;
    private JButton btAfficher, btEffacer, btFermer;
    
    public TestGUI() {
        // Forcer le mode non-headless
        System.setProperty("java.awt.headless", "false");
        
        frame = new JFrame("Application Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());
        
        tfmsg = new JTextField(20);
        btAfficher = new JButton("Afficher");
        btEffacer = new JButton("Effacer");
        btFermer = new JButton("Fermer");
        
        btAfficher.addActionListener(e -> tfmsg.setText("Coucou, Bonjour!"));
        btEffacer.addActionListener(e -> tfmsg.setText(""));
        btFermer.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
        
        frame.add(new JLabel("Message:"));
        frame.add(tfmsg);
        frame.add(btAfficher);
        frame.add(btEffacer);
        frame.add(btFermer);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        System.out.println("✅ Fenêtre créée avec succès!");
    }
    
    public static void main(String[] args) {
        // Forcer DISPLAY
        if(System.getenv("DISPLAY") == null || System.getenv("DISPLAY").isEmpty()) {
            System.out.println("Configuration de DISPLAY...");
            System.setProperty("DISPLAY", ":99");
        }
        
        System.out.println("DISPLAY = " + System.getenv("DISPLAY"));
        System.out.println("Lancement de l'application...");
        
        SwingUtilities.invokeLater(() -> new TestGUI());
    }
}