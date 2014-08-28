/**
 *@author oawofeso
 */

import java.util.Observer;
import java.util.Observable;
import javax.swing.SwingWorker;

public class Controller extends SwingWorker implements Observer{
    
    //private static final String HOST = "pc.cs.purdue.edu";
    //private static final int PORT = 1337;
    private static final String CONNECT_STRING = "connect monitor";
    private Connector connector;
    private Model model;
    private View view;
    
    public Controller (Model model, View view, int port, String host) {
        connector = new Connector(host, port, CONNECT_STRING, this);
        this.model = model;
        this.view = view;
        execute();
    }
    
    protected Object doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100); // Tune as appropriate
            view.repaint(); // Causes paintComponent(...) to be invoked on EDT
        }
    }
    
    
    public void update(Observable arg1, Object arg2) {
        
        String message = (String) arg2;
        parseMessage(message);
        //System.out.println(message);
        
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
                    break;
                case "error":
                    //errorMessageHandler(messageArray);
                    break;
                case "reset":
                    //resetMessageHandler(messageArray);
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
        volunteer.startMoving(destination, timeReq);
    }
    public void walkingMessageHandler(String[] messageArray) {
        Volunteer volunteer = model.getVolunteerByName(messageArray[1]);
        Request request = model.getRequestByName(messageArray[2]);
        long timeReq = Long.parseLong(messageArray[5]);
        volunteer.startWalking(request, timeReq);
        model.removeRequest(request);
    }
    public void deleteMessageHandler(String[] messageArray) {
        Volunteer volunteer = model.getVolunteerByName(messageArray[1]);
        model.removeVolunteer(volunteer);
    }
    
    /*public static void main(String[] args) {
     Controller c = new Controller();
     
     
     }*/
}