import javax.swing.JFrame;
import javax.swing.*;
import java.awt.BorderLayout;

public class EmptyFrame {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(640, 480);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE);
        jf.setTitle("Empty Frame");
        jf.setVisible(true);
        JMenuBar jmb = new JMenuBar();
        JMenu jm1 = new JMenu("File");
        JMenu jm1_1 = new JMenu("New");
        jm1.add(jm1_1);
        JMenu jm2 = new JMenu("Edit");
        jmb.add(jm1);
        jmb.add(jm2);
        jf.add(jmb, BorderLayout.NORTH);
    }
}
