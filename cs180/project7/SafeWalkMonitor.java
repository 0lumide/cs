/**
 *@author oawofeso
 */
import javax.swing.SwingUtilities;


public class SafeWalkMonitor implements Runnable{
    private static String host;
    private static int port;
    
    public SafeWalkMonitor(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public static void main(String[] args) {
        
        // Pass args to new SafeWalkView instance running on Event Dispatch Thread...
        port = Integer.parseInt(args[1]);
        SwingUtilities.invokeLater(new SafeWalkMonitor(args[0], port));
    }
    
    
    
    /**
     * Run on the EDT, creating model, view, and controller.
     */
    public void run() {
        Model model = new Model();
        View view = new View(model);
        new Controller(model, view, port, host);
    }
    
    
}
