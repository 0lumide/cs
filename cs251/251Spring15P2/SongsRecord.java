
public class SongsRecord {
	private int nSongs;
	
	private Song[] songsHeap;
	private int[] songsCount;
	private int[] songsPosition;

	public SongsRecord(int nSongs) {
		this.nSongs = nSongs;
		
		this.songsHeap = new Song[nSongs];
		this.songsCount = new int[nSongs];
		this.songsPosition = new int[nSongs];
	}
		
	public void addSongEntry(int songID, int numberOfTimesPlayed) {
		this.songsHeap[songID] = new Song(songID, numberOfTimesPlayed);
	}
	
	public Song[] getHeapOfSongs() {
		return this.songsHeap;
	}
	
	public int[] getPositionsOfSongs() {
		return this.songsPosition;
	}
	
	public int[] getSongPlayedCounts() {
		return this.songsCount;
	}
	
	public int getNumberOfSongs() {
		return this.nSongs;
	}

	public void process() {
		/* TODO Write code to:
			(1) Turn 'songsHeap' into a valid heap
			(2) Populate 'songsCount' and 'songsPosition' correctly */
	}
	
	public void addSongOccurrence(int songID) {
		/* TODO Write code to:
			(1) Read 'songsPosition' and get the position of the song in the heap. Then increase the count by 1. After increasing the count by 1, move the song up if required and ensure that the heap invariant is maintained.
			(2) Modify 'songsPosition' and 'songsCount' accordingly to ensure that everything is consistent.
		*/
	}

	public String getTopTwo() {
		String output = "";
		
		// Get top song
		output = Integer.toString(songsHeap[0].getSongID());
		output += " (" + Integer.toString(songsHeap[0].getNumberOfTimesPlayedSoFar())  + ")";
		
		// Get runner up
		int runnerUp = 2;
		if(songsHeap[1].getNumberOfTimesPlayedSoFar() > songsHeap[2].getNumberOfTimesPlayedSoFar() ||
				(songsHeap[1].getNumberOfTimesPlayedSoFar() == songsHeap[2].getNumberOfTimesPlayedSoFar() 
				&& songsHeap[1].getSongID() < songsHeap[2].getSongID()))
			runnerUp = 1;
		output += "; " + Integer.toString(songsHeap[runnerUp].getSongID());
		output += " (" + Integer.toString(songsHeap[runnerUp].getNumberOfTimesPlayedSoFar())  + ")";

		return output;
	}
}
