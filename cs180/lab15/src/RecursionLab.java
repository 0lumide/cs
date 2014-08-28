import java.io.File;
public class RecursionLab {

	public static int power(int a, int b) {
		if (b == 0)
			return 1;
		b--;
		return a * power(a,b);
	}
	
	public static int mysterySeries(int i, int j) {
		if((i < 0) || (j < 0) || (i < j))
			return 0;
		else if((i == j) || (j == 0))
			return 1;
		else
			return (mysterySeries(--i, j) + mysterySeries(i, --j));
	}
	
	public static double goldenRatio(int n) {
		if (n == 0) 
			return 1;
		return 1 + (1 / goldenRatio(--n));
	}
	
	public static int fileCount(File dir) {
		File[] temp = dir.listFiles();
		int count = 1;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].isFile())
				count++;
			else if (temp[i].isDirectory()) {
				count = count + fileCount(temp[i]);
			}
		}
		return count;
	}
	public static void main(String[] args) {
		System.out.println(goldenRatio(100));
	}
	
}
