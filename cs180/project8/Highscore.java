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

public class Highscore {
    //private Model model;
    private HashMap<String, Integer> nameNScore;
    private HashSet<String> absent;
    public final int NUM_OF_HIGHSCORES = 10;
    public Object lock;
    
    
    public Highscore() {
        //this.model = model;
        nameNScore = new HashMap<String, Integer>();
        absent = new HashSet<String>();
        lock = new Object();
    }
    
    public void removeAbsent(String name) {
        absent.remove(name);
    }
    
    public void removeVolunteer(String name) {
        absent.add(name);
    }
    
    public void addScore(Volunteer vol) {
        int score = vol.getScore();
        String name = vol.getName();
        nameNScore.put(name, score);
        removeAbsent(name);
    }    
    
    public boolean isAbsent(String name) {
        return(absent.contains(name));
    }
    
    public HashMap<String, Integer> getHighscores() {
        LinkedHashMap<String, Integer> highscores = new LinkedHashMap<String, Integer>();
        int count = 1;
        while ((count < nameNScore.size()) && (count < (NUM_OF_HIGHSCORES + 1))) {
            Iterator<String> it = nameNScore.keySet().iterator();
            
            while (it.hasNext() && (count < (NUM_OF_HIGHSCORES + 1))) {
                Iterator<String> it2 = nameNScore.keySet().iterator();
                String name = it.next();
                int score = nameNScore.get(name);
                int pos = 1;
                while (it2.hasNext() && (pos <= count)) {
                    if (score < nameNScore.get(it2.next()))
                        pos++;
                }
                if (pos == count) {
                    highscores.put(name, score);
                    count++;
                }
                
            }
        }
        return highscores;
    }
    
    
    public void reset() {
        nameNScore.clear();
        absent.clear();
    }
}