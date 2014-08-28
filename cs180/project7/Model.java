/**
 *@author oawofeso
 */
import java.util.*;
import java.util.concurrent.locks.*;

public class Model {
    
    public Object lock;  //Lock lock = new ReentrantLock();
    private HashSet<Location> locations = new HashSet<Location>();
    private HashSet<Request> requests = new HashSet<Request>();
    private HashSet<Volunteer> volunteers = new HashSet<Volunteer>();
    private HashMap<String, Location> buildingNLocation = new HashMap<String, Location>();
    private HashMap<String, Request> nameNRequest = new HashMap<String, Request>();
    private HashMap<String, Volunteer> nameNVolunteer = new HashMap<String, Volunteer>();
    
    public Model() {
        lock = new Object();
    }
    
    public void addLocation(Location location) {
        Location existing = getLocationByName(location.getName());
        if (existing != null)
            locations.remove(existing);
        locations.add(location);
        buildingNLocation.put(location.getName(), location);
    }
    
    public void addRequest(Request request) {
        Request existing = getRequestByName(request.getName());
        if (existing != null)
            requests.remove(existing);
        requests.add(request);
        nameNRequest.put(request.getName(), request);
    }
    
    public void addVolunteer(Volunteer volunteer) {
        Volunteer existing = getVolunteerByName(volunteer.getName());
        if (existing != null)
            volunteers.remove(existing);
        volunteers.add(volunteer);
        nameNVolunteer.put(volunteer.getName(), volunteer);
    }
    
    public Location getLocationByName(String name) {
        return buildingNLocation.get(name);
    }
    
    public HashSet<Location> getLocations() {
        return locations;
    }
    
    public Request getRequestByName(String name) {
        return nameNRequest.get(name);
    }
    
    public HashSet<Request> getRequests() {
        return requests;
    }
    
    public Volunteer getVolunteerByName(String name) {
        return nameNVolunteer.get(name);
    }
    
    public HashSet<Volunteer> getVolunteers() {
        return volunteers;
    }
    
    public void removeRequest(Request request) {
        requests.remove(request);
        nameNRequest.remove(request.getName());
        //remove it from the start location to which it is attached
        Location startLocation = request.getStart();
        if (startLocation != null)
            startLocation.removeRequest(request);
    }
    
    public void removeVolunteer(Volunteer volunteer) {
        String existingName = volunteer.getName();
        if (existingName != null) {
            volunteers.remove(volunteer);
            nameNVolunteer.remove(volunteer.getName());
            Location currentLocation = volunteer.getCurrentLocation();
            if (currentLocation != null) {
                currentLocation.removeVolunteer(volunteer);
            }
        }
    }
}