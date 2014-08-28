/**
 * Project1 -- Robot Position Calculator 
 * 
 * This program creates two Robot objects and 
 * calculates the distance between them. 
 * 
 * @author Olumide Awofeso
 * @lab L14 David Runyan
 * @date 1/30/2014
 */

import java.util.Scanner;

/**
 * The name of each robot is provided as input from the user.
 * The position of each robot is assigned randomly through the constructor.
 * The method distance returns the distance between this robot and the other robot.
 */


public class Robot {
    
    /**
     * The name of the Robot.
     */
    String name;
    
    /**
     * The x-coordinate of the Robot location.
     */
    double x;
    
    /**
     * The y-coordinate of the Robot location.
     */
    double y;
    
    /**
     * Constructor to assign values for instance variables
     * name assigned using the passed argument. Member variables
     * x and y are assigned random real values in range [0, 1).
     *
     * @param name the robot name
     */
    public Robot(String name) {
        // TODO assign this.name
        // TODO assign this.x and this.y using separate calls to Math.random()
        this.name = name;
        this.x = Math.random();
        this.y = Math.random();
    }
    
    /*
     * Returns the robot name.
     *
     * @returns a string containing no whitespace
     */
    public String getName() {
        return this.name;
    }
    
    /*
     * Returns the x-coordinate of the robot location
     *
     * @returns a real value in range [0, 1)
     */
    public double getX() {
        return this.x;
    }
    
    /*
     * Returns the y-coordinate of the robot location
     *
     * @returns a real value in range [0, 1)
     */
    public double getY() {
        return this.y;
    }
    
    
    
    
    /*
     * Calculate the Manhattan distance between the robot's location
     * and the location specified by coordinates (x, y), i.e.:
     *
     * @param xCoord a real value for x-coordinate
     * @param yCoord a real value for y-coordinate
     * @returns a real value representing the distance
     */
    public double distance(double xCoord, double yCoord) {
        //insert your code here
        double distance;
        //double x = getX();
        //double y = getY();
        distance = Math.abs(xCoord-x)+Math.abs(yCoord - y);
        return distance;  
    }
    
    /*
     * main() Method
     * The main method must do the following:
     * Input Name for robOne
     * Input Name for robTwo
     * Create the robOne object
     * Create the robTwo object
     * Display position of robOne
     * Display position of robTwo
     * Calculate the distance between both robots by calling distance function
     * Display distance between the robots
     *
     * @param args can be ignored.
     */
    public static void main(String[] args) {
        // TODO input name for robOne
        // TODO input name for robTwo
        // TODO Create Robot object, e.g. robOne
        // TODO Create Robot object, e.g. robTwo
        // TODO Print out robOne name and position in line 1
        // TODO Print out robTwo name and position in line 2
        // TODO Call distance(double xCoord, double yCoord) method of robOne
        // TODO Print out the Manhattan distance between the robots in line 3
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the names of the robots: ");
        String robOneName = scanner.next();//Line();
        String robTwoName = scanner.next();
        Robot robOne = new Robot(robOneName);
        Robot robTwo = new Robot(robTwoName);
        System.out.println("Robot " +robOneName+ " : ( " +robOne.getX()+" , "+robOne.getY()+ " )");
        System.out.println("Robot " +robTwoName+ " : ( " +robTwo.getX()+" , "+robTwo.getY()+ " )");
        //System.out.println(robTwoName);
        double distance = robOne.distance(robTwo.getX(),robTwo.getY());
        System.out.println("Distance : "+distance);
        // Note: be sure to adhere to the output format described below
    }
}
