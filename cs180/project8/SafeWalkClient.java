/**
 * Project 8 -- SafeWalk Simulator -- Artificial Intelligence
 *
 * This Program Works with the safewalk simulator.
 * It decides what actions to take, and it's send to the safewalk simulator
 *
 *@author oawofeso
 *
 *
 * @date April 24, 2014
 *
 */
import javax.swing.SwingUtilities;


public class SafeWalkClient implements Runnable{
    private static String host;
    private static int port;
    private static String key;
    private static String nickname;
    
    public SafeWalkClient(String host, int port, String key, String nickname) {
        this.host = host;
        this.port = port;
        this.key = key;
        this.nickname = nickname;
    }
    
    public static void main(String[] args) {
        
        // Pass args to new SafeWalkView instance running on Event Dispatch Thread...
        host = args[0];
        port = Integer.parseInt(args[1]);
        key = args[2];
        nickname = args[3];
        //SwingUtilities.invokeLater(new SafeWalkClient(host, port, key, nickname));
        (new Thread(new SafeWalkClient(host, port, key, nickname))).start();
        
    }
    
    
    
    /**
     * Run on the EDT, creating model, view, and controller.
     */
    public void run() {
        Model model = new Model();
        //View view = new View(model);
        new Controller(model, host, port, key, nickname);
    }
    
    
}
