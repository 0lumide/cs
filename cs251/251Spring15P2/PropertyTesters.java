public class PropertyTesters {

	 /* This tests if the 'songs' array is a valid heap */
	public static boolean testForHeapness (Song[] songs) {
		int parentIndex; //Position of the nodes parent in the array
		int parentPlayCount; //Number of times the parent song has been played
		int songPlayCount; //Number of times the song has been played

		//loop from last leaf to first child
		for(int i = songs.length - 1; i > 0; i--){
			parentIndex = (i - 1)/2; //No need to floor since integer operation
			parentPlayCount = songs[parentIndex].getNumberOfTimesPlayedSoFar();
			songPlayCount = songs[i].getNumberOfTimesPlayedSoFar();
			//Check if leaf value is greater than its parents value
			if(songPlayCount > parentPlayCount){
				return false;
			}
			//Check if values are equal, then if ordered by id
			else if(songPlayCount == parentPlayCount){
				if(songs[i].getSongID() < songs[parentIndex].getSongID()){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean testDataConsistency (SongsRecord record) {
		int numberOfSongs = record.getNumberOfSongs();
		Song[] songsHeap = record.getHeapOfSongs();
		int[] songPositions = record.getPositionsOfSongs();
		int[] songsCounts = record.getSongPlayedCounts();
		
		boolean isConsistent = true;

		for(int i = 0; i < numberOfSongs && isConsistent; ++i) {
			isConsistent = isConsistent && (songsHeap[songPositions[i]].getNumberOfTimesPlayedSoFar() == songsCounts[i]); 
			isConsistent = isConsistent && (songPositions[songsHeap[i].getSongID()] == i);
		}

		return isConsistent;
	}
}
