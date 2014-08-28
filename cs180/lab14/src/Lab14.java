import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;


public class Lab14 {

	public static void printSentences() {
		Scanner sc = new Scanner(System.in);
		// TODO : you can store words in the following Queue
		Queue<String> q = new ArrayDeque<String>();
	    // Hint #1: as you read the sentences, parse them into words
		// and put the words into the queue
		while (sc.hasNextLine()) {
			boolean newline = false;
			String lineInput = sc.nextLine();
			//System.out.println(lineInput);
			String[] wordInput = lineInput.split(" ");
			//System.out.println(wordInput[1]);
			for (int i = 0; i < wordInput.length; i++) {
				q.add(wordInput[i]);
				if ((wordInput[i].charAt(wordInput[i].length() - 1) == '!') || (wordInput[i].charAt(wordInput[i].length() - 1) == '?') || (wordInput[i].charAt(wordInput[i].length() - 1) == '.')) {
					Iterator <String> it = q.iterator();
					while (it.hasNext()) {
						System.out.print(it.next() + " ");
						newline = true;
						//System.out.println(wordInput[i]);
					}
					q.clear();
				}
			}
			if (newline)
				System.out.println("");
		}
		
		// Hint #2: when you detect the end of sentence, get the words
		// from the queue until then and print them
	}

	public static String findBestMedalist(File fileName) throws FileNotFoundException {
		// TODO : read the file using the scanner
		// you can use the following scanner to read the file in 
		// the same way that you usually do.
		Scanner fileScan = new Scanner(fileName);
		Map<String, Integer> medalistPoints = new HashMap<String, Integer>();
		while (fileScan.hasNextLine()) {
			String lineText = fileScan.nextLine();
			// TODO : You can use this map to store the points for each medalist.
			String[] lineArray = lineText.split(" ");
			//String medalType = lineCatArray[0].split(" ")[1];
			for (int i = 2; i < lineArray.length; i++) {
				if ((lineArray[i] != "Gold:") && (lineArray[i] != "Silver:")){
					if (!medalistPoints.containsKey(lineArray[i])) 
						medalistPoints.put(lineArray[i], 0);
					if(i < 7){
						medalistPoints.put(lineArray[i], medalistPoints.get(lineArray[i]) + 2);
					}
					else{
						medalistPoints.put(lineArray[i], medalistPoints.get(lineArray[i]) + 1);
					}
				}
			}
			
			// TODO : Iterate through map to find the best Medalist 
		}
		Iterator<String> it = medalistPoints.keySet().iterator();
		int highscore = 0;
		String bestMedalist = null;
		while (it.hasNext()) {
			String name = it.next();
			if (medalistPoints.get(name) > highscore) {
				bestMedalist = name;
				highscore = medalistPoints.get(name);
			}
		}
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
