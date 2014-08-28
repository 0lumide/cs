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
public class Request {
    //Model model;
    String name;
    Location start;
    Location destination;
    int score;
    
    public Request (Model model, String name, Location start, Location destination, int value) {
        //this.model = model;
        this.name = name;
        this.start = start;
        this.destination = destination;
        this.score = value;
        model.addRequest(this);
        start.addRequest(this);
    }
    public String getName() {
        return this.name;
    }
    
    public Location getDestination() {
        return this.destination;
    }
    
    public Location getStart() {
        return this.start;
    }
    
    public int getValue() {
        return this.score;
    }
}