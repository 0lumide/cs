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

public class SafeWalkAI {
    public final boolean AGRESSIVE_MODE_DEFAULT = true;
    public final boolean CONSERVATIVE_MODE_DEFAULT = false;
    private boolean agressiveMode;
    private boolean conservativeMode;
    private boolean aboutToWalk;
    private Request whoToWalk;
    private Location myLocation;
    private double wastedDistance;
    public final int VOLUNTEER_THRESHOLD = 8;//This is usually about when conservative mode starts performing better than not Conservative
    Model model;
    private String nickname;
    //Object lock;//not needed
    
    public SafeWalkAI(Model model, String nickname) {
        this.model = model;
        this.agressiveMode = AGRESSIVE_MODE_DEFAULT;
        this.conservativeMode = CONSERVATIVE_MODE_DEFAULT;
        this.aboutToWalk = false;
        this.whoToWalk = null;
        this.myLocation = null;
        this.wastedDistance = 0;
        this.nickname = nickname;
        //this.lock = model.lock;
    }
    
    public String nextRequest(Model model, Volunteer myVolunteer) {
        /*if (model.getVolunteers().size() >= VOLUNTEER_THRESHOLD)
            setConservativeMode(true);
        else
            setConservativeMode(false);*/
        if (agressiveMode || !aboutToWalk) {
            Iterator<Request> it = (model.getRequests()).iterator();
            myLocation = myVolunteer.getCurrentLocation();
            LinkedList<Request> availableRequests = new LinkedList<Request>();
            double distanceWeightedScore;
            //double timeWeightedScore;
            if (aboutToWalk) {
                int score = whoToWalk.getValue();
                Location endLoc = whoToWalk.getDestination();
                distanceWeightedScore = (double)score / (myLocation.calculateDistance(endLoc));
                /*long timeA1 = model.getLocationNTime(myLocation.getName() + " " + endLoc.getName());
                 if (timeA1 != -1) {
                 timeWeightedScore = (double)score / (timeA1);
                 }*/
            }
            while (it.hasNext()) {
                Request currentReq = it.next(); //Iterates through each key
                Location startLoc = currentReq.getStart();
                Location endLoc = currentReq.getDestination();
                int score = currentReq.getValue();
                boolean addedReq = false;
                
                distanceWeightedScore = (double)score / (myLocation.calculateDistance(startLoc) + startLoc.calculateDistance(endLoc) + wastedDistance);
                //if available requests list is empty add to it
                if (availableRequests.isEmpty()) {
                    availableRequests.add(currentReq);
                    addedReq = true;
                }
                //If not empty add to list in correct position in order of weighted score
                else {
                    for (int i = 0; i < availableRequests.size(); i++) {
                        Request req = availableRequests.get(i);
                        
                        double weightedScore = distanceWeightedScore;
                        
                        double weightedScore2 = (double)req.getValue() / (myLocation.calculateDistance(req.getStart()) + req.getStart().calculateDistance(req.getDestination()) + wastedDistance);
                        if(weightedScore > weightedScore2) {
                            availableRequests.add(i, currentReq);
                            addedReq = true;
                            break;
                        }
                    }
                }
                if (!addedReq) {
                    availableRequests.add(currentReq);
                    addedReq = true;
                }
            }
            boolean isPossible;
            boolean relocate = true;
            Request possibleReq = null;
            boolean dontStop = true;
            
            for (int i = 0; i < availableRequests.size(); i++) {
                possibleReq = availableRequests.get(i);
                isPossible = true;
                if (!myLocation.getName().equals(possibleReq.getStart().getName()))
                    isPossible = checkPossible(myLocation, possibleReq);
                if (isPossible) {
                    relocate = false;
                    break;
                }
            }
            
            if (relocate) {
                aboutToWalk = false;
                wastedDistance = 0;
                return(relocate(myLocation.getName()));
            }
            else {
                if (myLocation == possibleReq.getStart()) {
                    aboutToWalk = false;
                    wastedDistance = 0;
                    return("walk " + possibleReq.getName());
                }
                else {
                    whoToWalk = possibleReq;
                    aboutToWalk = true;
                    wastedDistance = wastedDistance + myLocation.calculateDistance(possibleReq.getStart());
                    return(determineAction(myLocation, possibleReq.getStart()));
                }
            }
        }
        else {
            aboutToWalk = false;
            wastedDistance = 0;
            return("walk " + whoToWalk.getName());
        }
    }
    
    /*This method determines weather to move to the location end or to walk someone to that location
     * then performs the decision
     * 
     * */
    public String determineAction(Location start, Location end) {
        Iterator<Request> it = (start.getRequests()).iterator();
        int score = 0;
        Request finalReq = null;
        Request req;
        while (it.hasNext()) {
            req = it.next();
            if(req.getDestination() == end) {
                int temp = req.getValue();
                if (temp > score) {
                    score = temp;
                    finalReq = req;
                }
            }
        }
        if (score == 0) {
            return("move " + end.getName());
        }
        else {
            return("walk " + finalReq.getName());
        }
    }
    
    
    /*
     * This method determines if my volunteer would get to the Request req start location before the other volunteers
     * return true if possible and false otherwise
     * */ 
    public boolean checkPossible(Location loc, Request req) {
        Location dest = req.getStart();
        double myDist = loc.calculateDistance(dest);
        //long timeReq = 0;
        //long timeLeft = 0;
        
        //long myTimeReq = model.getLocationNTime(dest.getName() + " " + loc.getName());
        Iterator<Volunteer> it = dest.getAllMovingPersons().iterator();
        boolean possible = true;
        if (!conservativeMode) {
            while (it.hasNext() && possible) {
                Volunteer v = it.next();
                if (myDist >= dest.getMovingPerson(v)) {
                    /*System.out.println(loc.getName() + " to " + dest.getName() + " is " + myDist);
                     System.out.println(v.getName() + " to " + dest.getName() + " is " + dest.getMovingPerson(v));*/
                    possible = false;
                }
            }
        }
        else {
            Iterator<Volunteer> it2 = model.getVolunteers().iterator();
            while (it2.hasNext() && possible) {
                Volunteer v = it2.next();
                if (!v.getName().equals(nickname)) {
                    if (v.getCurrentLocation() == null) {
                        if (myDist >= ((v.getFinalLocation().getMovingPerson(v)) + v.getFinalLocation().calculateDistance(dest))) {
                            possible = false;
                            //System.out.println(v.getName() + " will reach " + req.getName() + " before you");
                        }
                    }
                    else {
                        if (myDist >= dest.calculateDistance(v.getCurrentLocation())) {
                            //possible = false;
                            //System.out.println(v.getName() + " is closer to " + req.getName() + " than you are");
                        }
                    }
                }
            }
        }
        //if (!possible)
            //System.out.println(req.getName() + " is not possible");
        return possible;
    }
    
    /*
     * This method finds the location with the most requests and moves there
     * */
    public String relocate(String myLoc) {
        HashMap<String, Integer> hs = model.getRequestCount();
        Iterator<String> it = hs.keySet().iterator();
        String place = null;
        int num = 0;
        while (it.hasNext()) {
            String tempPlace = it.next();
            int temp = hs.get(tempPlace);
            if (temp > num) {
                num = temp;
                if (!myLoc.equals(tempPlace)) {
                    place = tempPlace;
                }
            }
        }
        System.out.println("I'm Relocating.");
        return("move " + place);
    }
    
    public void reset() {
        setAboutToWalk(false);
        setWhoToWalk(null);
        setMyLocation(null);
        setWastedDistance(0); 
    }
    
    //Begin set Methods
    public void setAgressiveMode(boolean b) {
        agressiveMode = b;
    }
    
    public void setConservativeMode(boolean b) {
        conservativeMode = b;
    }
    
    public void setAboutToWalk(boolean b) {
        aboutToWalk = b;
    }
    
    public void setWastedDistance(int val) {
        wastedDistance = val;
    }
    
    public void setWhoToWalk(Request req) {
        whoToWalk = req;
    }
    
    public void setMyLocation(Location loc) {
        myLocation = loc;
    }
    
    
}