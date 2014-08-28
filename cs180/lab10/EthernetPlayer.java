import java.util.ArrayList;

/**
 * Class EthernetPlayer
 */

public class EthernetPlayer extends MusicPlayer {
    
    private int deviceID;
    private int connStatus;
    
    
    static final int CONNECTED = 1;
    static final int NOT_CONNECTED = 0;
    
    private ArrayList<String> downloadList = new ArrayList<String>();
    
    
    public EthernetPlayer(int ID) { 
        super();
        deviceID = ID;
        connStatus = NOT_CONNECTED;
        
        downloadList.add("Dark Horse");
        downloadList.add("Royals");
        downloadList.add("Counting Stars");
        downloadList.add("Let Her Go");
        downloadList.add("The Fox");
        System.out.println("Creating EthernetPlayer: " + deviceID);
    }
    
    /**
     *  @override turnOn
     * 
     */
    
    public void turnOn() {
        
        System.out.println("EthernetPlayer Powering ON");
        this.status = ON;
        this.connectToNetwork();
        
    }
    
    
    /**
     *  Connect to network
     * 
     */
    
    public void connectToNetwork() { 
        
        if (this.status == OFF) {}
        
        else if ( connStatus == CONNECTED) {
            System.out.println("Already Connected");
        }
        else {
            connStatus = CONNECTED;
            System.out.println("Connected to Network");
            System.out.println();
        }
        
    }
    
    /**
     *  @override play
     * 
     */
    
    public void play(int trackNumber) {
        
        try {
            
            System.out.println("Now Playing: " + playlist.get(trackNumber));
            this.status = PLAYING;
            
        }   catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter valid trackNo. " + e.getMessage() + " Invalid!");
            this.status = OFF;
        }
        
    }
    
    
    /**
     *  addTrackToPlaylist: Adds mentioned track to the end of playlist array
     * 
     */
    
    public void addToPlaylist(String trackName) throws ExceedsCapacityException {
        
        try {
            if (playlist.size() + 1 > PLAYLIST_SIZE) {
                throw new ExceedsCapacityException("Exceeds Capacity!"); 
            }
            playlist.add(trackName);
            
        }
        catch (ExceedsCapacityException e) {
            System.out.print("Sorry! Playlist is full! ");
            System.out.println("Because Exception: " + e.getMessage());
        }
        
    }
    
    
    /**
     *  deleteFromPlaylist: deletes track of give name from the playlist
     * 
     */
    
    public void deleteFromPlaylist(String trackName) throws TrackNotFoundException {
        try{
            
            //implement your code for deleting the given element from the playlist
            // Add your code here
                       
            if (playlist.contains(trackName)) {
                playlist.remove(trackName);
                
            }
            else {
                throw new TrackNotFoundException();
            }


            
        }
        catch (TrackNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     *  download: Downloads mentioned song from the given Download list and adds to playlist 
     * 
     */
    
    public void download(String trackName) {
        try{
            //implement this section of code to lookup tracName in the downloadlist and 
            //add it to the playlist using add method in Arraylist with following message
            
            
            System.out.println("Downloading..." + trackName);
            //else throw appropriate exception by referring to the cases handled
            if (downloadList.contains(trackName)) {    
                if (playlist.size() <= PLAYLIST_SIZE) {
                    playlist.add(trackName);
                }
                else {
                    throw new ExceedsCapacityException();
                }   
            }
            else
                throw new TrackNotFoundException();
            
        }
        catch (TrackNotFoundException e) {
            System.out.println("Sorry, we currently do not support the track you tried to download!");
            System.out.println("Because " + e.getMessage());
        }
        catch (ExceedsCapacityException e) {
            System.out.print("Sorry! Playlist is full! ");
            System.out.println("Because Exception: " + e.getMessage());
        }
        
    }
    
}
