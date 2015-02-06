public class PropertyTesters {
	
	public static boolean testForHeapness (Song[] songs) {
		/* TODO write code to test if the 'songs' array is a valid heap */
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
