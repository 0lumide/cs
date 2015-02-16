
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

	//This function basically treats the subarray from stop to the end as a heap and heapifies it, thereby pushing the max value in the subarray to the stop index
	private void heapifyNTabulate(int index, int stop){
		int parentIndex = (index - 1 - stop)/2 + stop; //No need to floor the result because integer operation

		//Check if parent exists
		if(parentIndex < stop)
			return;
		//Compare to parent, and swap if necessary
		if(songsHeap[index].getNumberOfTimesPlayedSoFar() > songsHeap[parentIndex].getNumberOfTimesPlayedSoFar()){
			//swap
			//I do not use songsCount in this method because, it's this method that initailizes it
			Song temp = songsHeap[parentIndex];
			songsHeap[parentIndex] = songsHeap[index];
			songsHeap[index] = temp;
		}
		else if(songsHeap[index].getNumberOfTimesPlayedSoFar() == songsHeap[parentIndex].getNumberOfTimesPlayedSoFar()){
			//Seperated the if else if and if statements for readability/clarity purposes
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
		heapifyNTabulate(index - 1, stop);
	}
	public void process() {
		//Since a sorted array is also a max heap I'll be sorting the array. I'll be using heap sort ironically

		//Start the recursive heapifyNTabulate function from the last leaf;
		for(int i = 0; i<songsHeap.length; i++){
			heapifyNTabulate(songsHeap.length - 1, i); //This pushes the largest node to the index position i 
		}
	}
	
	public void addSongOccurrence(int songID) {
		int bigger, smaller, biggerSongID;
		int index = songsPosition[songID];
		songsHeap[index].increasePlayedCountByOne();
		songsCount[songID]++;
		//Since the array would have been sorted right before the modification, I only have to find remove and insert the modifies song to its new position if need be then update the positions of the songs after it, if need be
		//Check if now bigger than previously bigger one
		if(index > 0){// Doesn't bother if song was previously the most played
			biggerSongID = songsHeap[index - 1].getSongID();
			bigger = songsCount[biggerSongID];
			smaller = songsCount[songID];
			Song temp = songsHeap[index];
			biggerSongID = songsHeap[index - 1].getSongID();
			if((bigger < smaller)||((bigger == smaller)&&(biggerSongID > songID))){
				//swap
				songsHeap[index] = songsHeap[index - 1];
				songsHeap[index - 1] = temp;
				//Update songsPosition
				songsPosition[biggerSongID]++;
				songsPosition[songID]--;
			}
		}
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
