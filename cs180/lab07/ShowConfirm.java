import javax.swing.JOptionPane;

public class ShowConfirm {
    public static void main(String[] args) {
        int result;
        String[] results = { "Yes", "No", "Cancel" };
        
        // showConfirmDialog with default values and integer return value...
        result = JOptionPane.showConfirmDialog(null, 
                                               "Please confirm.");
        if (result == -1)
            System.out.printf("user closed window\n");
        else
            System.out.printf("result = %d, user pressed \"%s\"\n", result, results[result]);
        
        // showConfirmDialog with "Default" options...
        result = JOptionPane.showConfirmDialog(null,
                                               "Please confirm (default).",
                                               null,
                                               JOptionPane.DEFAULT_OPTION);
        if (result == -1)
            System.out.printf("user closed window\n");
        else
            System.out.printf("result = %d, user pressed \"%s\"\n", result, results[result]);
        
        // showConfirmDialog with "Yes" or "No" options...
        result = JOptionPane.showConfirmDialog(null,
                                               "Please confirm (Yes or No).", 
                                               null,
                                               JOptionPane.YES_NO_OPTION);
        if (result == -1)
            System.out.printf("user closed window\n");
        else
            System.out.printf("result = %d, user pressed \"%s\"\n", result, results[result]);
        
        // showConfirmDialog with "OK" or "Cancel" options (note OK == Yes)...
        result = JOptionPane.showConfirmDialog(null, 
                                               "Please confirm (OK or Cancel).",
                                               null,
                                               JOptionPane.OK_CANCEL_OPTION);
        if (result == -1)
            System.out.printf("user closed window\n");
        else
            System.out.printf("result = %d, user pressed \"%s\"\n", result, results[result]);
      
        String s = JOptionPane.showInputDialog(null, "Please provide an integer.");
        int value = Integer.parseInt(s);
        
        String parity = "odd";
        if (value % 2 == 0)
            parity = "even";
        
        String m = String.format("You gave %d, which is %s.\n", value, parity);    
        JOptionPane.showMessageDialog(null, m); 
        
    }
} 
