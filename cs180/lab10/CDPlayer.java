import java.util.ArrayList;

/**
 * Class CDPlayer
 */
public class CDPlayer extends MusicPlayer {
    
    private int deviceID;
    private int thisTrack;  
    
    /**
     * Constructor for CD-Player
     */
    public CDPlayer(int ID) {
        super();
        this.deviceID = ID;
        this.thisTrack = 0;
        System.out.println("Creating CDPlayer: " + deviceID);
        //Define your constructor here
        //Add code here



    }
    
    /**
     * Over-ride Method: turnOn
     */ 
    public void turnOn() {
        System.out.println("CDPlayer Powering ON");
        this.status = ON;
    }
    
    /**
     * Over-ride Method: turnOff
     */
    
    public void turnOff() {
        this.status = OFF;
        thisTrack = 0;
    }
    
    /**
     * Method to play next track in the playlist
     */
    
    public void nextTrack() {
        
        try {
             //Add your code here
            thisTrack++;
            System.out.println("Now Playing: "+playlist.get(thisTrack));
            //Implement your code with exception handling to print output given in handout
           
        } 
        catch (IndexOutOfBoundsException e) {
            System.out.println("End of playlist!");
            thisTrack = 0;
        }
    }
    
    /**
     * Method to play previous track in the playlist
     */
    
    public void previousTrack() {
        
        try {
            thisTrack--;
            System.out.println("Now Playing: " + playlist.get(thisTrack));
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("This is the first song! No more previous songs! ");
            thisTrack = 0;
        }
        
    } 
    
    /**
     * Method to play track in playlist by trackname
     */
    
    public void play(int trackNumber) {
        
        try {
            System.out.println("Now Playing: " + playlist.get(trackNumber));
            thisTrack = trackNumber;
            this.status = PLAYING;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter valid trackNo. " + e.getMessage() + " Invalid!");
            this.status = OFF;
        }
        
    }
    
}