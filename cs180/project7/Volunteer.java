/**
 *@author oawofeso
 */

public class Volunteer {
    //Model model;
    String name;
    int score;
    Location location;
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
        location.addVolunteer(this);
    }
    public String getName() {
        return this.name;
    }
    
    public Location getCurrentLocation() {
        return this.location;
    }
    
    public double[] getCurrentPosition() {
        /*long currentTime = System.currentTimeMillis();
         long timeElapsed = currentTime - startTime;
         if (timeElapsed < timeRequired) {
         double currY = start[1] + (double) ((timeElapsed / timeRequired) * (destination[1] - start[1]));
         double currX = start[0] + (double) ((timeElapsed / timeRequired) * (destination[0] - start[0]));
         return new double[] {currX, currY};
         }
         else {
         return destination;
         }*/
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
    
    public double[] getDestination() {
        return destination;
    }
    
    public String getRequester() {
        return requesterName;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public double[] getStart() {
        return start;
    }
    
    public void startMoving(Location destination, long timeReq) {
        this.timeRequired = timeReq;
        this.startTime = System.currentTimeMillis();
        this.start = (getCurrentLocation()).getXY();
        this.destination = destination.getXY();
        if (location != null)
            location.removeVolunteer(this);
        location = null;
    }
    
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
    }
}