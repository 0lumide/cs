
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
	private int getParentIndex(int i){
		return (i - 1)/2; //No need use floor since integer operation
	}

	private int getLeftChildIndex(int i){
		return (i*2 + 1);
	}

	private int getRightChildIndex(int i){
		return (i*2 + 2);
	}

	//This function basically treats the subarray from stop to the end as a heap and heapifies it, thereby pushing the max value in the subarray to the stop index
	private void heapifyNTabulate(int index, int stop){
		int parentIndex = (index - 1 - stop)/2 + stop; //No need to floor the result because integer operation

		//Check if parent exists
		if(parentIndex < stop)
			return;
		//Compare to parent, and swap if necessary
		if(songsHeap[index].getNumberOfTimesPlayedSoFar() > songsHeap[parentIndex].getNumberOfTimesPlayedSoFar()){
			//swap
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
			for(int o = 0; o < this.getHeapOfSongs().length; o++){
                                System.out.printf("%d ", this.getHeapOfSongs()[o].getNumberOfTimesPlayedSoFar());
                        }
			System.out.println("");

			heapifyNTabulate(songsHeap.length - 1, i); //This pushes the largest node to the index position i 
		}
	}
	
	public void addSongOccurrence(int songID) {
		/* TODO Write code to:
			(1) Read 'songsPosition' and get the position of the song in the heap. Then increase the count by 1. After increasing the count by 1, move the song up if required and ensure that the heap invariant is maintained.
			(2) Modify 'songsPosition' and 'songsCount' accordingly to ensure that everything is consistent.
		*/
		int index = songsPosition[songID];
		songsHeap[index].increasePlayedCountByOne();
		heapifyNTabulate(index, 0);
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
