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
//import java.util.Observer;
//import java.util.Observable;
//import javax.swing.SwingWorker;
import javax.swing.*;
//import java.util.Iterator;
//import java.util.LinkedList;

public class Controller implements Observer{
    
    //private static final String HOST = "pc.cs.purdue.edu";
    //private static final int PORT = 1337;
    private static String connectString;
    private Connector connector;
    private Model model;
    private String nickname;
    private View view;
    public static final int HIGHSCORE_LENGTH = 5;
    private ArrayList<String> highscoreNames;
    private boolean override;
    private boolean ignoreError;
    private String overrideCommand;
    SafeWalkAI ai;
    UserInput userInput;
    Highscore highscore;
    //private boolean reset;
    //private long endTime;
    
    
    public Controller (Model model, String host, int port, String key, String nickname) {
        this.nickname = nickname;
        connectString = String.format("connect %s", key);
        connector = new Connector(host, port, connectString, this);
        this.override = false;
        this.overrideCommand = null;
        this.model = model;
        this.ai = new SafeWalkAI(model, nickname);
        ignoreError = true;
        highscore = model.getHighscore();
        //This thread is to take input from the user at anytime
        //to change settings and override commnads
        userInput = new UserInput(model, this, ai);
        //SwingUtilities.invokeLater(userInput);
        (new Thread(userInput)).start();
        
        
        //execute();
    }
    
    public void setOverride(boolean b) {
        override = b;
    }
    public void setIgnoreError(boolean b) {
        ignoreError = b;
    }
    public void setOverrideCommand(String s) {
        overrideCommand = s;
    }
    
    
    public void update(Observable arg1, Object arg2) {
        
        String message = (String) arg2;
        parseMessage(message);
        
    }
    
    public void parseMessage(String message) {
        String[] messageArray = message.split(" ");
        
        synchronized (model.lock) {
            switch (messageArray[0]) {
                case "location":
                    locationMessageHandler(messageArray);
                    break;
                case "volunteer":
                    volunteerMessageHandler(messageArray);
                    break;
                case "request":
                    requestMessageHandler(messageArray);
                    break;
                case "moving":
                    movingMessageHandler(messageArray);
                    break;
                case "walking":
                    walkingMessageHandler(messageArray);
                    break;
                case "delete":
                    deleteMessageHandler(messageArray);
                    break;
                case "warning":
                    //warningMessageHandler(messageArray);
                    System.out.println("Warning message: " + message);
                    break;
                case "error":
                    System.out.println("I have to exit due to this error: " + message);
                    if (!ignoreError)
                        System.exit(1);
                    //errorMessageHandler(messageArray);
                    break;
                case "reset":
                    //resetMessageHandler(messageArray);
                    resetMessageHandler();
                    break;
                default:
                    //do nothing I guess
            }
        }
        
    }
    
    public void locationMessageHandler(String[] messageArray) {
        double x = Double.parseDouble(messageArray[2]);
        double y = Double.parseDouble(messageArray[3]);
        Location newLocation = new Location(model, messageArray[1], x, y);
        model.addLocation(newLocation);
    }
    public void volunteerMessageHandler(String[] messageArray) {    
        int score = Integer.parseInt(messageArray[3]);
        Location location = model.getLocationByName(messageArray[2]);
        Volunteer existing = model.getVolunteerByName(messageArray[1]);
        if (existing != null)
            model.removeVolunteer(existing);
        Volunteer newVolunteer = new Volunteer(model, messageArray[1], score, location);
        model.addVolunteer(newVolunteer);
        
        if (messageArray[1].equals(nickname)) {
            //System.out.println("here");
            if (override) {
                connector.writeLine(overrideCommand);
                System.out.println("Override: " + overrideCommand);
                override = false;
            }
            else {
                String bestAction = ai.nextRequest(model, newVolunteer);
                String[] bestActionSplit = bestAction.split(" ");
                if (bestActionSplit[0].equals("move"))
                    moveHandler(bestActionSplit[1]);
                else
                    walkHandler(bestActionSplit[1]);
            }
        }
        
    }
    public void requestMessageHandler(String[] messageArray) { 
        int value = Integer.parseInt(messageArray[4]);
        Location start = model.getLocationByName(messageArray[2]);
        Location destination = model.getLocationByName(messageArray[3]);
        Request newRequest = new Request(model, messageArray[1], start, destination, value);
        model.addRequest(newRequest);
    }
    public void movingMessageHandler(String[] messageArray) {
        Volunteer volunteer = model.getVolunteerByName(messageArray[1]);
        Location destination = model.getLocationByName(messageArray[3]);
        long timeReq = Long.parseLong(messageArray[4]);
        if (volunteer != null) {
            //model.setLocationTime(messageArray[3] + " " + messageArray[2], timeReq);
            if (!nickname.equals(volunteer.getName())) {
                synchronized(destination.lock) {
                    destination.addMovingPerson(volunteer);
                }
            }
            volunteer.startMoving(destination, timeReq);
        }
    }
    public void walkingMessageHandler(String[] messageArray) {
        Volunteer volunteer = model.getVolunteerByName(messageArray[1]);
        Request request = model.getRequestByName(messageArray[2]);
        long timeReq = Long.parseLong(messageArray[5]);
        Location destination = model.getLocationByName(messageArray[4]);
        if (volunteer != null) {
            volunteer.startWalking(request, timeReq);
            //model.setLocationTime(messageArray[4] + " " + messageArray[3], timeReq);
            if (!nickname.equals(volunteer.getName())) {
                synchronized(destination.lock) {
                    destination.addMovingPerson(volunteer);
                }
            }
        }
        model.removeRequest(request);
    }
    public void deleteMessageHandler(String[] messageArray) {
        Volunteer volunteer = model.getVolunteerByName(messageArray[1]);
        model.removeVolunteer(volunteer);
    }
    
    public void resetMessageHandler() {
        
        //HashSet<Request> requestsClone = (HashSet<Request>) model.getRequests().clone();
        HashSet<Request> requestsClone = new HashSet<Request>(model.getRequests());
        for (Request request : requestsClone)
            model.removeRequest(request);
        //HashSet<Volunteer> volunteersClone = (HashSet<Volunteer>) model.getVolunteers().clone();
        HashSet<Volunteer> volunteersClone = new HashSet<Volunteer>(model.getVolunteers());
        for (Volunteer volunteer : volunteersClone)
            model.removeVolunteer(volunteer);
        
        ai.reset();
        highscore.reset();
        System.out.println("Safewalk has just been reset, God speed, in this round");
        
    }
    
    
    public void walkHandler(String req) {
        //try {
        //Request who = model.getRequestByName(req);
        //Volunteer myVolunteer = model.getVolunteerByName(nickname);
        //endTime = myVolunteer.getDuration() + System.currentTimeMillis();
        //System.out.println(System.currentTimeMillis() + " walk " + req);
        connector.writeLine("walk " + req);
        //}catch(Exception e) {
        //    System.out.println("Exeption: " + e.getMessage());
        //}
        //System.out.println("Wasted Distance: " + wastedDistance);
    }
    
    public void moveHandler(String loc) {
        //Volunteer myVolunteer = model.getVolunteerByName(nickname);
        //endTime = myVolunteer.getDuration() + System.currentTimeMillis();
        //String where = loc.getName();
        //System.out.println(System.currentTimeMillis() + " move " + loc);
        connector.writeLine("move " + loc);
        //System.out.println("Wasted Distance: " + wastedDistance);
    }
    
    
}
