import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class Lab14 {

	public static void printSentences() {
		Scanner sc = new Scanner(System.in);
		// TODO : you can store words in the following Queue
		Queue<String> q = new ArrayDeque<String>();
	    // Hint #1: as you read the sentences, parse them into words
		// and put the words into the queue
		// Hint #2: when you detect the end of sentence, get the words
		// from the queue until then and print them
	}

	public static String findBestMedalist(File fileName) throws FileNotFoundException {
		// TODO : read the file using the scanner
		// you can use the following scanner to read the file in 
		// the same way that you usually do.
		Scanner fileScan = new Scanner(fileName);
		
		// TODO : You can use this map to store the points for each medalist.
		Map<String, Integer> medalistPoints = new HashMap<String, Integer>();
		
		// TODO : Iterate through map to find the best Medalist 
		String bestMedalist = null;
		return bestMedalist;
	}

	// main will NOT BE GRADED so it is just for you to test your code.
	public static void main(String[] args) throws FileNotFoundException {
		// testing the compete()
		File tweets = new File("lab14test");
		String winner = findBestMedalist(tweets);
		System.out.println("Winner is: " + winner);
        
		// for convenience, this is put at the end
		// because sentences are read in an "infinite" loop
		printSentences();
	}
}
