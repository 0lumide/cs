/**
 *@author oawofeso
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