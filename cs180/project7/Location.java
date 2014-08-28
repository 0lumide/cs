/**
 *@author oawofeso
 */
import java.util.*;



public class Location {
    //private Model model;
    private String name;
    private double[] xyCoor;
    private HashSet<Volunteer> volunteer = new HashSet<Volunteer>();
    private HashSet<Request> request = new HashSet<Request>();
    
    public Location(Model model, String name, double x, double y) {
        //store model
        //this.model = model;
        //store name
        this.name = name;
        //store x and y
        this.xyCoor = new double[]{x , y};
        model.addLocation(this);
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
    
    public void removeVolunteer(Volunteer vol) {
        volunteer.remove(vol);
        //model.removeVolunteer(vol);
    }  
}