import java.util.Scanner;

public class TreeParser {
	static int position;
	
	static String treeString;
	
	public static String getCleanedString(String text) {
		/* TODO: strip blanks in 'text' */
		int len = text.length();
		int j = 0;
		char[] goodChars = new char[len];

		for(int i = 0; i < len; i++){
			char c = text.charAt(i);
			if(isAllowedChar(c)){
				goodChars[j] = c;
				j++;
			}
		}
		return new String(goodChars, 0, j);

	}
	/* Start helper functions*/
	private static boolean isAlllowedChar(char c){
		if(c == '(')
			return true;
		else if(c == ')')
			return true;
		else if(c == ',')
			return true;
		else
			return(isAlpha(c) || is Number(c));
	}
	private static boolean isAlpha(char c){
		return(((c >= 'a')&&(c <= 'z'))||((c >= 'A')&&(c <= 'Z')));
	}
	private static boolean isNumber(char c){
		return((c >= '0')&&(c <= '9'));
	}
	private static int getValue(String s){
		String str = s.substring(1, s.length() - 1);
		int end = 0;
		for(int i = 0; i < str.length; i++){
			if(isNumber(str.charAt(i)))
				end++;
			else
				break;
		}
		String valueString = str.substring(0, end);
		return Interger.paseInt(valueString);
	}
	/* End helper functions */
	public static Node createTree(String treeRepresentationText) {
		/* TODO: build the tree by parsing 'treeRepresentationText' */
		String value, left, right;

	}
	
	public static String traversePath(Node root, String direction) {
		/* TODO: traverse path in tree denoted by 'direction' and print node labels */
		/* print a '*' when path would leave the tree */
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int nCases = in.nextInt();
		in.nextLine();

		for(int i = 0; i < nCases; ++i) {
			String treeRepresentationText = in.nextLine();
			treeRepresentationText = getCleanedString(treeRepresentationText);
			System.out.println("Testcase " + (i + 1) + ": " + treeRepresentationText);
			
			Node root = createTree(treeRepresentationText);
			
			int nPaths = in.nextInt();
			in.nextLine();

			System.out.println("Output for testcase " + (i+1));
			for(int j = 0; j < nPaths; ++j) {
				String path = in.nextLine();
				path = getCleanedString(path);

				String output = traversePath(root, path);
				
				System.out.println(path.trim() + ": " + output.trim());
			}
		}
		in.close();
	}
}
