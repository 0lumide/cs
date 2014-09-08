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
import java.util.*;
import javax.swing.SwingUtilities;

public class UserInput implements Runnable{
    Controller controller;
    SafeWalkAI ai;
    View view;
    Model model;
    Highscore highscore;
    
    public UserInput (Model model, Controller controller, SafeWalkAI ai) {
        this.controller = controller;
        this.ai = ai;
        this.model = model;
        highscore = model.getHighscore();
    }
    
    public void run() {
        while (true) {
            /*Thread.sleep(100); // Tune as appropriate
             //view.repaint(); // Causes paintComponent(...) to be invoked on EDT*/
            Scanner s = new Scanner(System.in);
            String command = s.nextLine();
            if("viewGUI".equals(command)) {
                view = new View(model);
                SwingUtilities.invokeLater(view);
                //(new Thread(view)).start();
                //This is the thread that repaints the view if activated
                Thread thread = new Thread(){
                    public void run() {
                        while (view.isOpen()) {
                            try {
                                Thread.sleep(100);
                                view.repaint();
                                //System.out.println("painting" + Thread.currentThread().getId());
                            }
                            catch (Exception e) {
                                System.out.println("Exception: " + e.getMessage());
                            }
                        } 
                    }
                };
                
                thread.start();
                
            }
            else if("closeGUI".equals(command)) {
                if (view != null) {
                    view.closeFrame();
                }
            }
            else if("mode agressive".equals(command)) {
                ai.setAgressiveMode(true);
                System.out.println("Agressive mode activated");
            }
            else if("ignore error".equals(command)) {
                controller.setIgnoreError(true);
                System.out.println("Error messages are now ignored");
            }
            else if("exit on error".equals(command)) {
                controller.setIgnoreError(false);
                System.out.println("Error messages no longer ignored");
            }
            else if("mode notagressive".equals(command)) {
                ai.setAgressiveMode(false);
                System.out.println("Agressive mode deactivated");
            }
            else if("mode conservative".equals(command)) {
                ai.setConservativeMode(true);
                System.out.println("Conservative mode activated");
            }
            else if("mode notconservative".equals(command)) {
                ai.setConservativeMode(false);
                System.out.println("Conservative mode deactivated");
            }
            else if("mode normal".equals(command)) {
                ai.setConservativeMode(ai.CONSERVATIVE_MODE_DEFAULT);
                ai.setAgressiveMode(ai.AGRESSIVE_MODE_DEFAULT);
                System.out.println("mode has been set to normal");
            }
            else if("move".equals((command.split(" "))[0]) || "walk".equals((command.split(" "))[0])) {
                controller.setOverride(true);
                ai.setAboutToWalk(false);
                ai.setWastedDistance(0); 
                controller.setOverrideCommand(command);
            }
            else if("highscore".equals(command)) {
                HashMap<String, Integer> highscores = highscore.getHighscores();
                int pos = 1;
                Iterator<String> it = highscores.keySet().iterator();
                while (it.hasNext()){
                    String name = it.next();
                    int score = highscores.get(name);
                    System.out.printf("%d. %s: %d", pos, name, score);
                    pos++;
                    if(highscore.isAbsent(name)) {
                        System.out.println("\t - currently not safewalking");
                    }
                    else {
                        System.out.println(""); 
                    }
                }
            }
            else if ("help".equals(command)) {
                System.out.println("Supported commands:");
                System.out.println("\"highscore\"");
                System.out.println("\"openGUI\"");
                System.out.println("\"closeGUI\"");
                System.out.println("\"mode agressive\"");
                System.out.println("\"mode notagressive\"");
                System.out.println("\"mode conservative\"");
                System.out.println("\"mode notconservative\"");
                System.out.println("\"mode normal\"");
                System.out.println("\"move and walk commands\"");
                System.out.println("\"exit on error\"");
                System.out.println("\"ignore error\"");
            }
            else {
                System.out.println("\"" + command + "\"" + " is not a supported command");
            }
            
            //Supported commands
            //highscore
            //openGUI
            //closeGUI
            //mode agressive
            //mode notagressive
            //mode conservative
            //mode notconservative
            //mode normal
            //move and walk commands
            
        }
    }
    
}