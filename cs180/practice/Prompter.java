import javax.swing.JOptionPane;

public class Prompter {
    
    public static void main(String[] args) {
 
        String s = JOptionPane.showInputDialog(null, "Please provide an integer.");
        int value = Integer.parseInt(s);
        
        String parity = "odd";
        if (value % 2 == 0)
            parity = "even";
        
        String m = String.format("You gave %d, which is %s.\n", value, parity);    
        JOptionPane.showMessageDialog(null, m);    
    }
}