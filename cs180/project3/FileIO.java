import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileIO {
	
	//initialize the image arrays
	private int[][] originalImage;
	private int m;
	private int n;
	//int[][] newImage;
	
	public FileIO(String filename) {
		//final String filename = "input.txt";
		final Charset encoding = StandardCharsets.UTF_8;
		List<String> content = null;
		
		
		// read data from text file
		Path path = Paths.get(filename);
	    try {
	    	content = Files.readAllLines(path, encoding);
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	    
	    String[] beforeHand = content.get(0).split("\\s+");
	    m = content.size();
	    n = beforeHand.length;
	    originalImage = new int[m][n];
	    //newImage = new int[m][n];
	    for (int i = 0; i < m; i++) { 
	    	String[] oneLine = content.get(i).split("\\s+");
	    	for (int j = 0; j < n; j++) { 
	    		originalImage[i][j] = Integer.parseInt(oneLine[j]);
	    	}
	    }
	}
	
	public int[][] getSourceImage() {
		return originalImage;
	}
	
	public int maxRows() {
		return m;
	}
	
	public int maxColumns() {
		return n;
	}
	

}
