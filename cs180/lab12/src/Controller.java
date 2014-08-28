/**
 * Implement a controller that updates the model and repaints the view.
 * 
 */

import javax.swing.SwingWorker;

public class Controller extends SwingWorker<Object, Object> {
    Model model;
    View view;
    
    /*
     * private static HashMap<String,String> building2locations = new
     * HashMap<String,String>(){};
     * 
     * 
     * private HashMap<String, String> volunteers2locations = new
     * HashMap<String, String>(){};
     * 
     * private HashMap<String, String> requests2locations = new HashMap<String,
     * String>(){};
     */
    
    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.addCoordinate("470 785");
        model.addCoordinate("1430 1370");
        model.addCoordinate("780 1070");
        model.addCoordinate("780 85");
        model.addCoordinate("1000 23");
        model.addCoordinate("1200 450");
        model.addCoordinate("1310 580");
        model.addCoordinate("575 1290");
        model.addCoordinate("1090 1070");
        model.addCoordinate("780 755");
        model.addCoordinate("1100 1370");
        model.addCoordinate("750 1300");
        model.addCoordinate("565 975");
        model.addCoordinate("1440 900");
        model.addCoordinate("585 200");
        
        model.addRequester("ar");
        model.addRequester("br");
        model.addRequester("cr");
        model.addRequester("dr");
        model.addRequester("er");
        model.addRequester("fr");
        model.addRequester("gr");
        model.addRequester("hr");
        model.addRequester("ir");
        model.addRequester("jr");
        model.addRequester("kr");
        model.addRequester("lr");
        model.addRequester("mr");
        model.addRequester("nr");
        model.addRequester("or");
        
        model.addVolunteer("av");
        model.addVolunteer("bv");
        model.addVolunteer("cv");
        model.addVolunteer("dv");
        model.addVolunteer("ev");
        model.addVolunteer("fv");
        model.addVolunteer("gv");
        model.addVolunteer("hv");
        model.addVolunteer("iv");
        model.addVolunteer("jv");
        model.addVolunteer("kv");
        model.addVolunteer("lv");
        model.addVolunteer("mv");
        model.addVolunteer("nv");
        model.addVolunteer("ov");
        
        model.addLocation2Coordinate("LWSN", "470 785");
        model.addLocation2Coordinate("PMU", "1430 1370");
        model.addLocation2Coordinate("CL50", "780 1070");
        model.addLocation2Coordinate("ARMS", "780 85");
        model.addLocation2Coordinate("PHYS", "1000 23");
        model.addLocation2Coordinate("MSEE", "1200 450");
        model.addLocation2Coordinate("EE", "1310 580");
        model.addLocation2Coordinate("BRNG", "575 1290");
        model.addLocation2Coordinate("WTHR", "1090 1070");
        model.addLocation2Coordinate("ELLT", "780 755");
        model.addLocation2Coordinate("STEW", "1100 1370");
        model.addLocation2Coordinate("UNIV", "750 1300");
        model.addLocation2Coordinate("HAAS", "565 975");
        model.addLocation2Coordinate("NUCL", "1440 900");
        model.addLocation2Coordinate("PUSH", "585 200");
        
        model.addVolunteer2Location("av", "LWSN");
        model.addVolunteer2Location("bv", "PMU");
        model.addVolunteer2Location("cv", "CL50");
        model.addVolunteer2Location("dv", "ARMS");
        model.addVolunteer2Location("ev", "PHYS");
        model.addVolunteer2Location("fv", "MSEE");
        model.addVolunteer2Location("gv", "EE");
        model.addVolunteer2Location("hv", "BRNG");
        model.addVolunteer2Location("iv", "WTHR");
        model.addVolunteer2Location("jv", "ELLT");
        model.addVolunteer2Location("kv", "STEW");
        model.addVolunteer2Location("lv", "UNIV");
        model.addVolunteer2Location("mv", "HAAS");
        model.addVolunteer2Location("nv", "NUCL");
        model.addVolunteer2Location("ov", "PUSH");
        
        model.addRequester2Location("ar", "LWSN");
        model.addRequester2Location("br", "PMU");
        model.addRequester2Location("cr", "CL50");
        model.addRequester2Location("dr", "ARMS");
        model.addRequester2Location("er", "PHYS");
        model.addRequester2Location("fr", "MSEE");
        model.addRequester2Location("gr", "EE");
        model.addRequester2Location("hr", "BRNG");
        model.addRequester2Location("ir", "WTHR");
        model.addRequester2Location("jr", "ELLT");
        model.addRequester2Location("kr", "STEW");
        model.addRequester2Location("lr", "UNIV");
        model.addRequester2Location("mr", "HAAS");
        model.addRequester2Location("nr", "NUCL");
        model.addRequester2Location("or", "PUSH");
        
        model.addMover("nv PUSH 10000" + " " + System.currentTimeMillis());
        model.addMover("ov LWSN 8000" + " " + System.currentTimeMillis());
        // Start thread to run doInBackground() method, which handles regular
        // updates of the view
        execute();
        
    }
    
    /**
     * This method is run on a background SwingWorker thread. It periodically
     * updates the display.
     */
    @Override
    protected Object doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100); // Tune as appropriate
            view.repaint(); // Causes paintComponent(...) to be invoked on EDT
        }
    }
    
}