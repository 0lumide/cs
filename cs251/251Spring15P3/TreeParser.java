import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreeParser {
	static int position;
	
	static String treeString;
	
	public static String getCleanedString(String text) {
		return text.replaceAll("\\s","");
	}
	
	private static Node parseAndCreateTree() {
		if (treeString.charAt(position) == '(') {
			if (treeString.charAt(position + 1) == ')') {
				position += 2;
				return null;
			}
			char val = treeString.charAt(position + 1);
			Node curNode = new Node(String.valueOf(val));
			position += 3;
			if (treeString.charAt(position - 1) == ')')
				return curNode;
			Node leftRoot = parseAndCreateTree();
			position += 1;
			Node rightRoot = parseAndCreateTree();
			position += 1;
			curNode.setLeftChild(leftRoot);
			curNode.setRightChild(rightRoot);
			return curNode;
		}
		return null;

	}
	
	public static Node createTree(String treeRepresentationText) {
		treeString = treeRepresentationText;
		position = 0;
		return parseAndCreateTree();
	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, Node needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object) 
		 * where the tree denoted by Node object 'needle' is found in the subject tree denoted by 
		 * node object 'haystack' */
		ArrayList<String> needles = new ArrayList<String>();
		return needles;
	}
	
	public static List<String> searchForNeedleInHaystack(Node haystack, String needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object) 
		 * where the non-branching tree denoted by the String 'needle' is found in the subject 
		 * tree denoted by the Node object 'haystack' */		
		return null;
	}
	
	public static List<String> approximateSearch(Node haystack, String needle) {
		/* TODO Write code to return a list of all positions (as a List<String> object)
		 * where the non-branching tree denoted by the String 'needle' is 'approximately' found
		 * in the subject tree denoted by the Node object 'haystack' */
		return null;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int nCases = in.nextInt();
		in.nextLine();

		for(int i = 0; i < nCases; ++i) {
			String treeRepresentationText = in.nextLine();
			treeRepresentationText = getCleanedString(treeRepresentationText);
			System.out.println("Testcase " + (i + 1) + ": " + treeRepresentationText);
			
			Node rootSearchTree = createTree(treeRepresentationText);
			
			int nPaths = in.nextInt();
			in.nextLine();

			System.out.println("Output for testcase " + (i+1));
			for(int j = 0; j < nPaths; ++j) {
				String searchInput = in.nextLine();
				searchInput = searchInput.trim();
				
				String searchOption = searchInput.substring(0, 1);
				String searchPattern = searchInput.substring(2);
				
				searchPattern = getCleanedString(searchPattern);
				
				System.out.println("Query " + (j+1) + ": " + searchPattern);

				List<String> occurences;
				if(searchOption.equals("t")) { // Tree search
					Node rootPatternTree = createTree(searchPattern);
					occurences = searchForNeedleInHaystack(rootSearchTree, rootPatternTree);
				} else if(searchOption.equals("p")) { // Path search
					occurences = searchForNeedleInHaystack(rootSearchTree, searchPattern);
				} else { // Approximate Search
					occurences = approximateSearch(rootSearchTree, searchPattern);
				}
				Collections.sort(occurences);
				for(String position: occurences) {
					System.out.println(position);
				}
			}
		}
		in.close();
	}
}
