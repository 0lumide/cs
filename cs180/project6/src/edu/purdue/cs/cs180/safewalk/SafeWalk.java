package edu.purdue.cs.cs180.safewalk;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.content.Context;
import android.text.Html;
import android.graphics.Color;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Purdue Computer Science SafeWalk Application for CS 18000 (Spring 2014)
 * 
 * Search this file for "STEP" items to complete the project.
 */
public class SafeWalk extends Activity implements Observer {
    // STEP 1: Update KEY and NICKNAME with your assigned key and choice of nickname.
    private static final String KEY = "k850954";
    private static final String NICKNAME = "cs180rocks";
    // END 1
    private static final String HOST = "pc.cs.purdue.edu";
    private static final int PORT = 1337;
    
    private Connector connector;
    private ArrayList<String> logs;
    private ArrayList<String> locations;
    private ArrayList<String> requests;
    private String currentLocation;
    private String score;
    
    private TextView locationTextView;
    private Spinner locationsSpinner;
    private TextView scoreTextView;
    private Spinner requestsSpinner;
    private Button moveSubmit;
    private Button requestSubmit;
    private TextView logsTextView;
    
    private Handler messageHandler;
    private SafeWalk safeWalk = this;
    
    private String whoToWalk = "";
    private String whereToWalk = "";
    private boolean aboutToWalk = false;
    private boolean onTheMove = false;
    private boolean walking = false;
    private boolean botMode = false;
    private ArrayList<String> styledLogs = new ArrayList<String>();
    private ArrayList<String> favPeople = new ArrayList<String>();

    /* 
     * This method is called when an Android application begins or is reset. (Think of it like "main".)
     */

    
        
    public void printWarning(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message + "!", duration);
        toast.show();   
    }
    
    public void printMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();   
    }
    
    public void walkNextPerson() {
        String unparsedRequester = requests.get(1);
        String[] requesterDetails = unparsedRequester.split(" ");
        //
        // Get requester and volunteers locations
        String requesterLocation = requesterDetails[1];
        String requesterName = requesterDetails[0];
        String requesterDestination = requesterDetails[2];
        String volunteerLocation = currentLocation;
        // Check if volunteer and requester are at same location and take appropriate action
        if (!(volunteerLocation.equals(requesterLocation))) {
            aboutToWalk = true;
            whoToWalk = requesterName;
            whereToWalk = requesterDestination;
            connector.writeLine("move " + requesterLocation);
            locationTextView.setText("Moving to " + requesterLocation);
            onTheMove = true;
        }
        else {
            aboutToWalk = false;
            connector.writeLine("walk " + requesterName);
            locationTextView.setText("Walking " + requesterName + " to " + requesterLocation);
            walking = true;
        }
    }
    
    public String styleLog(String log) {
        String[] parameters;
        String name;
        parameters = log.split(" ");
        if (!(parameters[0].equals("location"))) {
            name = parameters[1];
            if ((parameters[0].equals("volunteer")) || (parameters[0].equals("walking"))) {
                if (name.equals(NICKNAME)) {
                    log = "<font color='green'>" + log + "</font>";
                }
            }
            else if (parameters[0].equals("request")) {
                if ((!favPeople.isEmpty()) && (favPeople.contains(name))) {
                    log = "<font color='blue'>" + log + "</font>";
                }
            }
            
        }
        return log;
    }
    
    public void sortLocation() {
        String[] tempArray = new String[locations.size()];
        tempArray = locations.toArray(tempArray);
        Arrays.sort(tempArray);
        locations = new ArrayList<String>(Arrays.asList(tempArray));
    }
    
    
    //end of my defined methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // The GUI is defined in res/layout/main.xml
        favPeople.add("GTA_Varun");    
        favPeople.add("GTA_Rithesh");
        favPeople.add("Prof_Prabhakar");
        favPeople.add("GTA_Sherwood");
        favPeople.add("Prof_Perouli");
        favPeople.add("GTA_Runyan");
        favPeople.add("GTA_Shankar");
        
        // Establish connection to the SafeWalk server and initialize data structures (the model)...
        connector = new Connector(HOST, PORT, String.format("connect %s", KEY), this);
        requests = new ArrayList<String>();
        locations = new ArrayList<String>();
        logs = new ArrayList<String>();
        
        // Get references to the "widgets" that make up the GUI...
        locationTextView = (TextView) findViewById(R.id.locationText);
        locationsSpinner = (Spinner) findViewById(R.id.moveSpinner);
        scoreTextView = (TextView) findViewById(R.id.scoreText);
        requestsSpinner = (Spinner) findViewById(R.id.requestSpinner);
        moveSubmit = (Button) findViewById(R.id.submitMove);
        requestSubmit = (Button) findViewById(R.id.submitWalk);
        logsTextView = (TextView) findViewById(R.id.distancesTextMultiline);
        logsTextView.setMovementMethod(new ScrollingMovementMethod());
        
        // Callback method: Invoked when the user presses the "Move" button...
        moveSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // STEP 4: Get the selected string from the locationsSpinner and send a "move" command to the server...
                if (!onTheMove) {
                    String unparsedLocation = (String)locationsSpinner.getSelectedItem();
                    String[] locationArray = unparsedLocation.split(" ");
                    String location = locationArray[0];
                    if (! currentLocation.equals(location)) {
                        connector.writeLine("move " + location);
                        locationTextView.setText("Moving to " + location);
                        onTheMove = true;
                    }
                    else {
                        printWarning("You're already at " + location);
                    }
                }
                else {
                    if (walking) {
                        printWarning("You're currently walking someone");
                    }
                    else if (onTheMove) {
                        printWarning("You're already on the move"); 
                    }
                }
            }
        });
        
        // Callback method: Invoked when the user presses the "Walk" button...
        requestSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(onTheMove)) {
                    // STEP 5: Get the selected request from the requestsSpinner and send a "walk" command to the server...
                    String unparsedRequester = (String) requestsSpinner.getSelectedItem();
                    String[] requesterDetails = unparsedRequester.split(" ");
                    //
                    // Get requester and volunteers locations
                    String requesterLocation = requesterDetails[1];
                    String requesterDestination = requesterDetails[2];
                    String requesterName = requesterDetails[0];
                    String volunteerLocation = currentLocation;
                    // Check if volunteer and requester are at same location and take appropriate action
                    if (!(volunteerLocation.equals(requesterLocation))) {
                        if (botMode) {
                            aboutToWalk = true;
                            whoToWalk = requesterName;
                            connector.writeLine("move " + requesterLocation);
                            onTheMove = true;
                            whereToWalk = requesterDestination;
                            locationTextView.setText("Moving to " + requesterLocation);
                        }
                        else {
                            printWarning("You're currently not at " + requesterName + "'s location");
                        }
                    }
                    else {
                        aboutToWalk = false;
                        connector.writeLine("walk " + requesterName);
                        locationTextView.setText("Walking " + requesterName + " to " + requesterDestination);
                        walking = true;
                    }
                }
                else {
                    if (walking) {
                        printWarning("You're already walking someone");
                    }
                    else if (onTheMove) {
                        printWarning("You're currently on the move"); 
                    }
                }
            }
        });
        //This is to enable or diable botMode
        requestSubmit.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (!(onTheMove)) {
                    if (!botMode) {
                        botMode = true;
                        printMessage("Bot mode has been enabled");
                    }
                }
                else if (botMode) {
                    botMode = false;
                    printMessage("Bot mode has been disabled");
                }
                
                return true;
            }
        });
        
        requestsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String location = requests.get(position).split(" ")[1];
                boolean dontStop = true;
                int i = 0;
                while (dontStop) {
                    if (location.equals(locations.get(i).split(" ")[0])) {
                        locationsSpinner.setSelection(i);
                        dontStop = false;
                    }
                    i++;
                }
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                
            }
            
        });

        // Create a MessageHandler object to allow communication between the Connector thread and the Android UI
        // thread...
        messageHandler = new MessageHandler();
    }
    
    /**
     * Close the connection to the server. This method is called automatically when the application resets.
     */
    @Override
    public void onStop() {
        connector.close();
        super.onStop();
    };
    
    /**
     * Receive messages from the server on the Connector thread and pass them along to the Android UT thread for
     * processing. This method is part of the Observer pattern. Messages sent from the Simulator to the Android are
     * passed to this method by the Connector class.
     */
    public void update(Observable arg1, Object arg2) {
        String message = (String) arg2;
        Log.w("SafeWalkMessage", message);
        
        // Since this method is invoked from the Connector class thread, we need to pass the message
        // through a message handler to the Android UI thread.
        android.os.Message m = new android.os.Message();
        m.obj = message;
        messageHandler.sendMessage(m);
    }
    
    /**
     * This nested class is needed to allow incoming messages from the Simulator, which arrive on one thread, to be
     * dispatched for handling on the main Android UI thread.
     */
    class MessageHandler extends Handler {
        
        /**
         * Process incoming message. Split the message into words, then dispatch into appropriate method for handling.
         */
        @Override
        public void handleMessage(android.os.Message m) {
            // Add the incoming message to the logs...
            String message = (String) m.obj;
            logs.add(message + "\n");
            
            // Convert the logs array to a single String, strip out all the special characters, and put in "Log"
            // view for user to see...
            if (styledLogs.isEmpty()) {
                String temp;
                for (int i = 0; i < logs.size(); i++) {
                    temp = logs.get(i).replace("\n", "");
                    styledLogs.add(styleLog(temp) + "<br/>");
                }
            }
            else {
                styledLogs.add(styleLog(message) + "<br/>");
            }
            
            String processedStyledLogs = styledLogs.toString().replaceAll(",|\\]", "").replaceAll("\\[", " ");
            logsTextView.setText(Html.fromHtml(processedStyledLogs));
            
//            String processedLogs = logs.toString().replaceAll(",|\\]", "").replaceAll("\\[", " ");
//            logsTextView.setText(processedLogs);
            
            
            // Split the incoming message into fields and dispatch on the first field...
            String[] fields = message.split(" ");
            if (fields[0].equals("location"))
                handleLocationMessage(fields);
            else if (fields[0].equals("request"))
                handleRequestMessage(fields);
            else if (fields[0].equals("volunteer"))
                handleVolunteerMessage(fields);
            else if (fields[0].equals("walking"))
                handleWalkingMessage(fields);
        }
        
        /**
         * Handle a "location" message from the server. Add it to the list of locations to display.
         */
        private void handleLocationMessage(String[] fields) {
            String name = fields[1];
            Double x = Double.parseDouble(fields[2]);
            Double y = Double.parseDouble(fields[3]);
            
            // STEP 2: Create a String representation of the "location" message, add it to the locations ArrayList...
            String location = name + " (" + x + ", " + y + ")";
            locations.add(location);
            sortLocation();
            // Update the locations spinner with the current list of locations...
            ArrayAdapter<String> locationsArrayAdapter = new ArrayAdapter<String>(safeWalk,
                                                                                  android.R.layout.simple_spinner_dropdown_item, locations);
            locationsSpinner.setAdapter(locationsArrayAdapter);
        }
        
        /**
         * Handle a "request" message from the server. Add it to the list of requests to display.
         */
        private void handleRequestMessage(String[] fields) {
            String name = fields[1];
            String fromLocation = fields[2];
            String toLocation = fields[3];
            int value = Integer.parseInt(fields[4]);
            
            // STEP 3: Create a String representation of the "request" message, add it to the requests ArrayList...
            String requestMessage = name + " " + fromLocation + " " + toLocation + " " + value;
            requests.add(requestMessage);
            // Update the requests spinner with the current list of requests...
            ArrayAdapter<String> requestsArrayAdapter = new ArrayAdapter<String>(safeWalk,
                                                                                 android.R.layout.simple_spinner_dropdown_item, requests);
            requestsSpinner.setAdapter(requestsArrayAdapter);
            
        }
        
        /**
         * Handle a "volunteer" message from the server. Just check to see if it is "me".
         */
        private void handleVolunteerMessage(String[] fields) {
            // STEP 6: Check to see if the "volunteer" message identifies "me" (compare to NICKNAME). If so, set the
            // currentLocation and score variables and use the setText(...) method to update the locationTextView and
            // scoreTextView widgets.
            onTheMove = false;
            walking = false;
            String volunteerName = fields[1];
            String volunteerLocation = fields[2];
            String score = fields[3];
            if (volunteerName.equals(NICKNAME)) {
                currentLocation = volunteerLocation;
                scoreTextView.setText(score);
                onTheMove = false;
                walking = false;
                locationTextView.setText(volunteerLocation);
                if (botMode && aboutToWalk) {
                    connector.writeLine("walk " + whoToWalk);
                    onTheMove = true;
                    walking = true;
                    locationTextView.setText("Walking " + whoToWalk + " to " + whereToWalk);
                    whoToWalk = "";
                    aboutToWalk = false;
                }
                else if (botMode && (!aboutToWalk)) {
                    walkNextPerson();
                }
            }


        }
        
        /**
         * Handle a "walking" message from the server. Remove the corresponding "request" message from the system, since
         * it is now being satisfied.
         */
        private void handleWalkingMessage(String[] fields) {
            String requester = fields[2];
            
            // STEP 7: Loop through the requests ArrayList, looking for one that matches the requester of this message.
            // If found, delete from the ArrayList.
            String[] requestDetails;
            String requestName;
            
            for (int i = 0; i < requests.size(); i++) {
                requestDetails = (requests.get(i)).split(" ");
                requestName = requestDetails[0];
                //connector.writeLine(requests.get(i));
                if (requestName.equals(requester)) {
                    requests.remove(i);
                    if (botMode) {
                        whoToWalk = "";
                        aboutToWalk = false;
                    }
                    i = requests.size();
                }            
            }
            // Update the requests spinner with the current list of requests...
            ArrayAdapter<String> requestsArrayAdapter = new ArrayAdapter<String>(safeWalk,
                                                                                 android.R.layout.simple_spinner_dropdown_item, requests);
            requestsSpinner.setAdapter(requestsArrayAdapter);
        }
        

    }
}
