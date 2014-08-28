/**
 * The model of the SafeWalkMonitor, allows adding locations, adding volunteers
 * or requests to specific locations, matching a volunteer with a request at a
 * given location.
 * 
 */

import java.util.HashMap;
import java.util.HashSet;


public class Model {
    
    private static HashMap<String,String> location2coordinate = new HashMap<String,String> ();
    private static HashSet<String> volunteers = new HashSet<String>();
    
    private HashSet<String> requesters = new HashSet<String>();
    
    private HashSet<String> coordinates = new HashSet<String>();
    private HashSet<String> movers = new HashSet<String> ();
    
    private HashMap<String, String> volunteer2location = new HashMap<String, String>();
    
    private HashMap<String, String> requester2location = new HashMap<String, String>();
    
    
    
    /**
     * Returns the set of volunteers in this model.
     * 
     * @return the HashSet<Volunteer> of volunteers; can iterate through the volunteers with this value
     */
    public HashSet<String> getVolunteers() {
        return volunteers;
    }
    
    
    /**
     * Returns the set of requests in this model.
     * 
     * @return the HashSet<Request> of requests; can iterate through the requests with this value
     */
    public  HashSet<String> getRequesters() {
        return requesters;
    }
    
    
    /**
     * Returns the set of Locations in this model.
     * 
     * @return the HashSet<Location> of locations; can iterate through the locations in this model
     */
    public HashSet<String> getCoordinates() {
        return coordinates;
    }
    
    public HashMap<String,String> getVolunteer2Location() {
        return volunteer2location;
    }
    
    public HashMap<String,String> getLocation2Coordinate() {
        return location2coordinate;
    }
    
    public HashMap<String,String> getRequester2Location() {
        return requester2location;
    }
    public HashSet<String> getMovers() {
        return movers;
    }
    
    public void addRequester(String request) {
        requesters.add(request);
    }
    
    public void addCoordinate(String coordinate) {
        coordinates.add(coordinate);
    }
    
    public void addMover(String mover) {
        movers.add(mover);
    }
    public void addVolunteer(String volunteer) {
        volunteers.add(volunteer);
    }
    public void addLocation2Coordinate(String building, String location) {
        location2coordinate.put(building, location);
    }
    public void addVolunteer2Location(String volunteer, String location) {
        volunteer2location.put(volunteer,location);
    }
    public void addRequester2Location(String requester, String location) {
        requester2location.put(requester,location);
    }
    
    public double[] getVolunteerPosition(String mover) {
        String [] fields = mover.split(" ");
        String volunteer = fields[0];
        String startLocation = volunteer2location.get(volunteer); 
        String startLoc = location2coordinate.get(startLocation);
        String[] startLocSplit = startLoc.split(" ");
        
        double[] start = new double[2];
        start[0] = Double.parseDouble(startLocSplit[0]);
        start[1] = Double.parseDouble(startLocSplit[1]);
        int xStart = (int) Math.round((float) start[0]);
        int yStart = (int) Math.round((float) start[1]);
        String destination = fields[1];
        long timeStarted = Long.parseLong(fields[3]);
        long timeRequired = Long.parseLong(fields[2]) ;
        String coordinate = location2coordinate.get(destination);
        String [] destinationCoord = coordinate.split(" ");
        double destX = Double.parseDouble(destinationCoord[0]);
        double destY = Double.parseDouble(destinationCoord[1]);
        double xLength = destX - xStart;
        double yLength = destY - yStart;
        
        double[] position = new double[2];
        
        long time = System.currentTimeMillis() - timeStarted;
        if (time >= timeRequired) {
            position[0] = destX;
            position[1] = destY;
        } else {
            position[0] = xLength * time / timeRequired + xStart;
            position[1] = yLength * time / timeRequired + yStart;
        }
        return position;
    }
    
}