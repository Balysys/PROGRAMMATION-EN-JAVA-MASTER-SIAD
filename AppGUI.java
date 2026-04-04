import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mon Application Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 150);
        frame.setLayout(new FlowLayout());
        
        JTextField tf = new JTextField(20);
        JButton b1 = new JButton("Afficher");
        JButton b2 = new JButton("Effacer");
        JButton b3 = new JButton("Fermer");
        
        b1.addActionListener(e -> tf.setText("Coucou, Bonjour!"));
        b2.addActionListener(e -> tf.setText(""));
        b3.addActionListener(e -> System.exit(0));
        
        frame.add(new JLabel("Message:"));
        frame.add(tf);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("GUI lancée!");
    }
}
