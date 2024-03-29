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



public class Location {
    //private Model model;
    private String name;
    private double[] xyCoor;
    private HashSet<Volunteer> volunteer = new HashSet<Volunteer>();
    private HashSet<Request> request = new HashSet<Request>();
    private HashSet<Volunteer> movingPeople = new HashSet<Volunteer>();
    public Object lock;
    
    public Location(Model model, String name, double x, double y) {
        //store model
        //this.model = model;
        //store name
        this.name = name;
        //store x and y
        this.xyCoor = new double[]{x , y};
        model.addLocation(this);
        lock = new Object();
    }
    
    public void addRequest(Request req) {
        request.add(req);
    }
    
    public void addVolunteer(Volunteer v) {
        this.volunteer.add(v);
    }
    
    public String getName() {
        return name;
    }
    
    public HashSet<Request> getRequests() {
        return request;
    }
    
    public HashSet<Volunteer> getVolunteers() {
        return volunteer;
    }
    
    public double[] getXY() {
        return xyCoor;
    }
    
    public void removeRequest(Request req) {
        request.remove(req);
        //model.removeRequest(req);
    }    
    
    public void removeMovingPerson(Volunteer vol) {
        movingPeople.remove(vol);
        //model.removeVolunteer(vol);
    }
    
    public void removeVolunteer(Volunteer vol) {
        volunteer.remove(vol);
        //model.removeVolunteer(vol);
    }  
    public void addMovingPerson(Volunteer vol) {
        movingPeople.add(vol);
    }
    
    public HashSet<Volunteer> getAllMovingPersons() {
        return(movingPeople);
    }
    
    public double getMovingPerson(Volunteer vol) {
        //System.out.println(vol.getName() + ": " + vol.getDuration());
        double[] xy1 = this.getXY();
        double[] xy2 = vol.getCurrentPosition();
        double x = xy1[0] - xy2[0];
        double y = xy1[1] - xy2[1];
        return(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }
    
    public double calculateDistance(Location loc) {
        double[] xy1 = this.getXY();
        double[] xy2 = loc.getXY();
        double x = xy1[0] - xy2[0];
        double y = xy1[1] - xy2[1];
        return(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }
}