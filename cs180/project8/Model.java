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
import java.util.concurrent.locks.*;

public class Model {
    
    public Object lock;  //Lock lock = new ReentrantLock();
    private HashSet<Location> locations = new HashSet<Location>();
    private HashSet<Request> requests = new HashSet<Request>();
    private HashSet<Volunteer> volunteers = new HashSet<Volunteer>();
    private HashMap<String, Location> buildingNLocation = new HashMap<String, Location>();
    private HashMap<String, Request> nameNRequest = new HashMap<String, Request>();
    private HashMap<String, Volunteer> nameNVolunteer = new HashMap<String, Volunteer>();
    private HashMap<String, Integer> locationNVolunteerCount = new HashMap<String, Integer>();
    private HashMap<String, Long> locationNTime = new HashMap<String, Long>();
    private boolean timesComplete = false;
    private boolean locationComplete = false;
    private Highscore highscore;
    //public  final int NUM_COMBINATION = 105;
    public int numCombination = 0;
    public Model() {
        lock = new Object();
        highscore = new Highscore();
    }
    
    public Highscore getHighscore() {
        return highscore;   
    }
    public void addLocation(Location location) {
        Location existing = getLocationByName(location.getName());
        if (existing != null) {
            locations.remove(existing);
        }
        locations.add(location);
        setRequestCountToZero(location);
        buildingNLocation.put(location.getName(), location);
        locationComplete = false;
        timesComplete = false;
    }
    
    
    public void addRequest(Request request) {
        Request existing = getRequestByName(request.getName());
        if (existing != null) {
            requests.remove(existing);
            //removeRequest(existing);
        }
        requests.add(request);
        nameNRequest.put(request.getName(), request);
        if (locationNVolunteerCount.containsKey(request.getStart().getName())) {
            locationNVolunteerCount.put(request.getStart().getName(), locationNVolunteerCount.get(request.getStart().getName()) + 1);
        }else {
            locationNVolunteerCount.put(request.getStart().getName(), 1);
        }
    }
    
    public void addVolunteer(Volunteer volunteer) {
        Volunteer existing = getVolunteerByName(volunteer.getName());
        if (existing != null) {
            volunteers.remove(existing);
            if (existing.getFinalLocation() != null) {
                existing.getFinalLocation().removeMovingPerson(volunteer);
                //removeVolunteer(existing);
            }
        }
        volunteers.add(volunteer);
        nameNVolunteer.put(volunteer.getName(), volunteer);
        highscore.addScore(volunteer);
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
        if (startLocation != null) {
            startLocation.removeRequest(request);
            locationNVolunteerCount.put(startLocation.getName(), locationNVolunteerCount.get(startLocation.getName()) - 1);
        }
    }
    
    public void removeVolunteer(Volunteer volunteer) {
        if (volunteer != null) {
            String existingName = volunteer.getName();
            if (existingName != null) {
                volunteers.remove(volunteer);
                nameNVolunteer.remove(volunteer.getName());
                Location currentLocation = volunteer.getCurrentLocation();
                if (currentLocation != null) {
                    currentLocation.removeVolunteer(volunteer);
                }
                if (volunteer.getFinalLocation() != null) {
                    volunteer.getFinalLocation().removeMovingPerson(volunteer);
                }
            }
            highscore.removeVolunteer(volunteer.getName());
        }
    }
    
    public void setRequestCountToZero(Location loc) {
        locationNVolunteerCount.put(loc.getName(), 0);
    }
    
    public long getLocationNTime(String locations) {
        String[] locs = locations.split(" ");
        if((locs[0].compareTo(locs[1])) < 0) {
            locations = locs[1] + locs[0];
        }
        if(timesComplete && locationNVolunteerCount.containsKey(locations))
            return locationNVolunteerCount.get(locations);
        else 
            return - 1;
    }
    
    public void setLocationTime(String locations, long time) {
        String[] locs = locations.split(" ");
        if((locs[0].compareTo(locs[1])) < 0) {
            locations = locs[1] + locs[0];
        }
        locationNTime.put(locations, time);
        if (locationNTime.size() == numCombination) {
            timesComplete = true;
            System.out.println("Agressive can now be activated");
        }
    }
    
    public HashMap<String, Integer> getRequestCount() {
        return locationNVolunteerCount;
    }
}