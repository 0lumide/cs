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
	private static boolean isAllowedChar(char c){
		if(c == '(')
			return true;
		else if(c == ')')
			return true;
		else if(c == ',')
			return true;
		else
			return(isAlpha(c) || isNumber(c));
	}
	private static boolean isAlpha(char c){
		return(((c >= 'a')&&(c <= 'z'))||((c >= 'A')&&(c <= 'Z')));
	}
	private static boolean isNumber(char c){
		return((c >= '0')&&(c <= '9'));
	}
	private static String parseValue(String s){
		int len = s.length();
		String str = s.substring(1, len - 1);
		int end = 0;
		for(int i = 0; i < str.length(); i++){
			if(isNumber(str.charAt(i)))
				end++;
			else
				break;
		}
		return(str.substring(0, end));
	}
	private static String[] parseLeftRight(String s){
		//Strips the first and last parenthesis from the string
		String str = s.substring(1, s.length() - 1);
		int end = 0;
		int start = -1;
		boolean noRight = false;
		boolean noLeft = false;
		boolean nodeFound = false;
		int track = -1;
		int i;
		char c;
		String[] leftRight = new String[]{"",""};
		for(i = 0; i < str.length(); i++){
			c = str.charAt(i);
			if(c == '('){
				if(start == -1){
					track = 0;
					start = i;
				}
				nodeFound = true;
				track++;
			}
			else if((c == ')') && nodeFound){
				track--;
			}
			if((c == ',')&&!nodeFound){
				if(!noLeft)
					noLeft = true;
				else{
					start = end;
					break;
				}
			}
			else{
				noLeft = false;
			}
			if(track == 0){
				end = i + 1;
				break;
			}
		}
		if(start == -1)
			start = 0;
		//This sets the left string to the string representation of the left tree including the parenthesis
		leftRight[0] = str.substring(start, end);
		start = -1;
		track = -1;
		end = 0;
		nodeFound = false;
		for(i++; i < str.length(); i++){
			c = str.charAt(i);
			if(c == '('){
				if(track == -1)
					track = 0;
				track++;
				if(start == -1){
					start = i;
				}
			}
			else if(c == ')'){
				track--;
			}
			if(track == 0){
				end = i + 1;
				break;
			}
		}
		//This sets the right string to the string representation of the right tree including the parenthesis
		if(start == -1)
			start = end;
		leftRight[1] = str.substring(start, end);
		return leftRight;
	}
	/* End helper functions */
	public static Node createTree(String treeRepresentationText) {
		/* TODO: build the tree by parsing 'treeRepresentationText' */
		String value, left, right;
		Node n;
		value = parseValue(treeRepresentationText);
		String[] leftRight = parseLeftRight(treeRepresentationText);
		left = leftRight[0];
		right = leftRight[1];
		if(value.length() > 0)
			n = new Node(Integer.parseInt(value));
		else
			n = new Node();
		if(left.length() > 2)
			n.setLeftChild(createTree(left));
		if(right.length() > 2)
			n.setRightChild(createTree(right));		
		return n;
	}
	
	public static String traversePath(Node root, String direction) {
		/* TODO: traverse path in tree denoted by 'direction' and print node labels */
		/* print a '*' when path would leave the tree */
		int len = direction.length();
		char c;
		Node n = null;
		String subDirect, value;
		/*if(root != null)
			value = root.getid() + "";*/
		if(len > 0){
			c = Character.toUpperCase(direction.charAt(0));
			subDirect = direction.substring(1, len);
			if(root != null){
				if(c == 'L')
					n = root.getLeftChild();
				else
					n = root.getRightChild();
				value = root.getid() + " ";
			}
			else{
				return "*";
			}
			return value + traversePath(n, subDirect);
		}
		else if(root != null)
			return root.getid() + "";
		else
			return "*";
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
