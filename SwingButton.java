import javax.swing.*;

public class SwingButton extends JFrame {
    public SwingButton() {
        JButton b = new JButton("OK");
        add(b);
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new SwingButton();
    }
}