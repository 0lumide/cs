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

public class Volunteer {
    //Model model;
    String name;
    int score;
    Location location;
    Location finalLocation;
    long startTime;
    long timeRequired;
    double[] start;
    double[] destination;
    String requesterName;
    Model model;
    
    
    public Volunteer (Model model, String name, int score, Location location) {
        //this.model = model;
        this.name = name;
        this.score = score;
        this.location = location;
        this.model = model;
        model.addVolunteer(this);
        if (location != null)
            location.addVolunteer(this);
    }
    
    /**
     * returns the name of the Volunteer.
     *
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * returns the location (Location Object) of the Volunteer.
     *
     */
    public Location getCurrentLocation() {
        return this.location;
    }
    
    /**
     * returns the destination (Location Object) of the Volunteer.
     *
     */
    public Location getFinalLocation() {
        return this.finalLocation;
    }
    
    /**
     * returns the current XY coordinates of of the Volunteer.
     * 
     * @param xLength distance between the destination and
     * start point along the x axis
     * @param yLength distance between the destination and
     * start point along the y axis
     * @param position A double that holds the double representing
     * the current XY coordinates of of the Volunteer
     * @return A double representing the current XY coordinates of of the Volunteer
     */
    public double[] getCurrentPosition() {
        double xLength = destination[0] - start[0];
        double yLength = destination[1] - start[1];
        
        double[] position = new double[2];
        
        long time = System.currentTimeMillis() - startTime;
        if (time >= timeRequired) {
            position[0] = destination[0];
            position[1] = destination[1];
        } else {
            position[0] = xLength * time / timeRequired + start[0];
            position[1] = yLength * time / timeRequired + start[1];
        }
        return position;
    }
    
    /**
     * returns the destination in xyCoorrdinates of the Volunteer.
     *
     */
    public double[] getDestination() {
        return destination;
    }
    
    /**
     * returns the name of the requester the Volunteer is walking.
     *
     */
    public String getRequester() {
        return requesterName;
    }
    
    /**
     * returns the score of the Volunteer.
     *
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * returns the duration of the Volunteers current walk.
     *
     */
    public long getDuration() {
        return this.timeRequired;
    }
    
    /**
     * returns the starting point of the Volunteer in xyCoorrdinates.
     */
    public double[] getStart() {
        return start;
    }
    
    /**
     * returns the time the volunteer started the current walk.
     *
     */     
    public long getStartTime() {
        return startTime;
    }
    
    /**
     * Stores the time required, starttime, starting coordinates and destination
     * It also sets the Volunteers current location to null to
     * signify the Volunteer is on the move
     *
     */
    public void startMoving(Location destination, long timeReq) {
        this.timeRequired = timeReq;
        this.startTime = System.currentTimeMillis();
        this.start = (getCurrentLocation()).getXY();
        this.destination = destination.getXY();
        if (location != null)
            location.removeVolunteer(this);
        location = null;
        finalLocation = destination;
    }
    
    /**
     * Stores the time required, starttime, starting coordinates, destination and the requesters name.
     * It removes the request the Volunteer is responding to from the model
     * It also sets the Volunteers current location to null to
     * signify the Volunteer is on the move
     *
     */
    public void startWalking(Request request, long timeReq) {
        model.removeRequest(request);
        this.timeRequired = timeReq;
        this.startTime = System.currentTimeMillis();
        this.start = (request.getStart()).getXY();
        this.destination = (request.getDestination()).getXY();
        this.requesterName = request.getName();
        if (location != null)
            location.removeVolunteer(this);
        location = null;
        finalLocation = request.getDestination();
    }
}