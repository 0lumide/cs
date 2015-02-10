
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
	
	//helper function
	private void heapifyNTabulate(int index){
		int parentIndex = (index - 1)/2; //No need to floor the result because integer operation

		//Check if parent exists
		if(parentIndex < 0)
			return;
		//Compare to parent, and swap if necessary
		if(songsHeap[index].getNumberOfTimesPlayedSoFar() > songsHeap[parentIndex].getNumberOfTimesPlayedSoFar()){
			//swap
			Song temp = songsHeap[parentIndex];
			songsHeap[parentIndex] = songsHeap[index];
			songsHeap[index] = temp;
		}
		else if(songsHeap[index].getNumberOfTimesPlayedSoFar() == songsHeap[parentIndex].getNumberOfTimesPlayedSoFar()){
			//Didn't seperated the if else if and if statements for readability/clarity purposes
			if(songsHeap[index].getSongID() < songsHeap[parentIndex].getSongID()){
				//swap
				Song temp = songsHeap[parentIndex];
				songsHeap[parentIndex] = songsHeap[index];
				songsHeap[index] = temp;
			}
		}
		//Store song play count;
		songsCount[songsHeap[index].getSongID()] = songsHeap[index].getNumberOfTimesPlayedSoFar();
		songsPosition[songsHeap[index].getSongID()] = index;
		heapifyNTabulate(index - 1);
	}
	public void process() {
		//Start the recursive heapifyNTabulate function from the last leaf;
		heapifyNTabulate(songsHeap.length - 1);
	}
	
	public void addSongOccurrence(int songID) {
		/* TODO Write code to:
			(1) Read 'songsPosition' and get the position of the song in the heap. Then increase the count by 1. After increasing the count by 1, move the song up if required and ensure that the heap invariant is maintained.
			(2) Modify 'songsPosition' and 'songsCount' accordingly to ensure that everything is consistent.
		*/
		int index = songsPosition[songID];
		songsHeap[index].increasePlayedCountByOne();
		heapifyNTabulate(index);
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
