/**
 * Project 3 -- Image Processing - Gaussian Filter
 *
 * This program generates a processed pixel data from the input.txt using specified mask.
 *
 * @author Olumide Awofeso
 * @lab L14
 * @date 2/13/2014
 */


/**
 * The Filter class has three type of methods dealing with different shape of target. 
 * The method for edge elements has been implemented for your understanding. 
 * You are expected to implement the constructor, the other two methods and the main method. 
 * Please DO NOT CHANGE the file IO part in the main() 
 * method: Use 'originalImage' for initializing variables in the constructor
 */
public class Filter {
    
    //default mask
    int[][] mask = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
    
    //the image used on this filter
    int[][] sourceImage;
    
    
    /**
     * This constructor initialize values.
     */
    public Filter(int[][] image) {
        // TODO store values to sourceImage
        this.sourceImage = image;
    }
    
    /**
     * Method: edge()
     * This method is used to deal with the edge.
     * The mode is representing different edges within a rectangle.
     * Mode 1: the top edge
     * Mode 2: the right edge
     * Mode 3: the bottom edge
     * Mode 4: the left edge
     * @param indexX the x coordinates of center pixel.
     * @param indexY the y coordinates of center pixel.
     * @param mode different mode for this filter
     * @returns the calculated pixel value
     */
    public int edge(int indexX, int indexY, int mode) { 
        // This method has been implemented for you as an example
        //for processing the edge elements of the matrix using the mask. 
        // You are expected to implement the other two methods
        
        int result = 0;
        int div1 = 0;
        int div2 = 0;
        int div3 = 0;
        int div4 = 0;
        switch (mode) {
            case 1:
                div1 = mask[1][0] + mask[1][1] + mask[1][2] + mask[2][0] + mask[2][1] + mask[2][2];
                result = (sourceImage[indexY][indexX - 1] * mask[1][0] + 
                          sourceImage[indexY][indexX] * mask[1][1] + 
                          sourceImage[indexY][indexX + 1] * mask[1][2] + 
                          sourceImage[indexY + 1][indexX - 1] * mask[2][0] +
                          sourceImage[indexY + 1][indexX] * mask[2][1] +
                          sourceImage[indexY + 1][indexX + 1] * mask[2][2]) / div1;
                break;
            case 2:
                div2 = mask[0][0] + mask[0][1] + mask[1][0] + mask[1][1] + mask[2][0] + mask[2][1];
                result = (sourceImage[indexY - 1][indexX - 1] * mask[0][0] + 
                          sourceImage[indexY - 1][indexX] * mask[0][1] + 
                          sourceImage[indexY][indexX - 1] * mask[1][0] + 
                          sourceImage[indexY][indexX] * mask[1][1] +
                          sourceImage[indexY + 1][indexX - 1] * mask[2][0] +
                          sourceImage[indexY + 1][indexX] * mask[2][1]) / div2;
                break;
            case 3:
                div3 = mask[0][0] + mask[0][1] + mask[0][2] + mask[1][0] + mask[1][1] + mask[1][2];
                result = (sourceImage[indexY - 1][indexX - 1] * mask[0][0] + 
                          sourceImage[indexY - 1][indexX] * mask[0][1] + 
                          sourceImage[indexY - 1][indexX + 1] * mask[0][2] + 
                          sourceImage[indexY][indexX - 1] * mask[1][0] +
                          sourceImage[indexY][indexX] * mask[1][1] +
                          sourceImage[indexY][indexX + 1] * mask[1][2]) / div3;
                break;
            case 4:
                div4 = mask[0][1] + mask[1][1] + mask[2][1] + mask[0][2] + mask[1][2] + mask[2][2];
                result = (sourceImage[indexY - 1][indexX] * mask[0][1] + 
                          sourceImage[indexY][indexX] * mask[1][1] + 
                          sourceImage[indexY + 1][indexX] * mask[2][1] + 
                          sourceImage[indexY - 1][indexX + 1] * mask[0][2] +
                          sourceImage[indexY][indexX + 1] * mask[1][2] +
                          sourceImage[indexY + 1][indexX + 1] * mask[2][2]) / div4;
                break;
        }
        
        return result; 
    }
    
    /**
     * Method: corner()
     * This method is used to deal with the corners.
     * The mode is representing different edges within a rectangle.
     * Mode 1: the top left corner
     * Mode 2: the top right corner
     * Mode 3: the bottom right corner
     * Mode 4: the bottom left corner
     * @param indexX the x coordinates of center pixel.
     * @param indexY the y coordinates of center pixel.
     * @param mode different mode for this filter
     * @returns the calculated pixel value
     */
    public int corner(int indexX, int indexY, int mode) { 
        // TODO implement the correct formula for corner filter
        int result = 0;
        int div = 1;
        switch (mode) {
            case 1:
                div = mask[1][1] + mask[1][2] + mask[2][1] + mask[2][2];
                result = (sourceImage[indexY][indexX] * mask[1][1] + 
                      sourceImage[indexY][indexX + 1] * mask[1][2] +
                      sourceImage[indexY + 1][indexX] * mask[2][1] +
                      sourceImage[indexY + 1][indexX + 1] * mask[2][2]) / div;
                break;
            case 2:
                div = mask[1][0] + mask[1][1] + mask[2][0] + mask[2][1];
                result = (sourceImage[indexY][indexX - 1] * mask[1][0] +
                      sourceImage[indexY][indexX] * mask[1][1] +
                      sourceImage[indexY + 1][indexX - 1] * mask[2][0] +
                      sourceImage[indexY + 1][indexX] * mask[2][1]) / div;
                break;
            case 3:
                div = mask[0][0] + mask[0][1] + mask[1][0] + mask[1][1];
                result = (sourceImage[indexY - 1][indexX - 1] * mask[0][0] +
                      sourceImage[indexY - 1][indexX] * mask[0][1] +
                      sourceImage[indexY][indexX - 1] * mask[1][0] +
                      sourceImage[indexY][indexX] * mask[1][1]) / div;
                break;
            case 4:
                div = mask[0][1] + mask[0][2] + mask[1][1] + mask[1][2];
                result = (sourceImage[indexY - 1][indexX] * mask[0][1] +
                      sourceImage[indexY][indexX] * mask[1][1] +
                      sourceImage[indexY - 1][indexX + 1] * mask[0][2] +
                      sourceImage[indexY][indexX + 1] * mask[1][2]) / div;
                break;           
        }        
        return result; 
    }
    
    /**
     * Method: center()
     * This method is used to deal with general situation.
     * @param indexX the x coordinates of center pixel.
     * @param indexY the y coordinates of center pixel.
     * @returns the calculated pixel value
     */
    public int center(int indexX, int indexY) { 
        // TODO implement the correct formula for filter with general situation
        int result = 0;
        int preResult = 0;
        int div = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                div += mask[i][j];
                preResult += (mask[i][j] * sourceImage[indexY + i - 1][indexX + j - 1]);
            }
        }
        result = preResult / div;
        return result; 
    }
    
    /**
     * The main method is going to print out processed pixel data to terminal.
     * m is the maximum number of rows in input pixel data
     * n is the maximum number of columns in input pixel data
     * @param args command line input, no need to take care of it
     */
    public static void main(String[] args) {
        final String filename = "input.txt";
        
        
        //initialize the image arrays
        int[][] originalImage;
        int[][] newImage;
        int m;
        int n;
        
        //User defined class to handle file IO
        FileIO pixelData = new FileIO(filename);
        originalImage = pixelData.getSourceImage();
        m = pixelData.maxRows();
        n = pixelData.maxColumns();
        newImage = new int[m][n];
        
        
        ////////////////////////////////////////////////////////////////
        // please don't change anything above here in main()
        //
        // m is the maximum number of rows in input pixel data
        // n is the maximum number of columns in input pixel data
        // originalImage is the two dimensional integer array of the source image
        // newImage is the two dimensional integer array of the processed image
        
        
        
        // TODO create a Filter object for image processing using originalImage
        Filter fil = new Filter(originalImage);
        // TODO create loops to circle through all the pixels in data
        // and use the condition blocks to apply different filter types by calling corresponding methods
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.print(newImage[i][j]);
                if (i == 0) {
                    if (j == 0) {
                        newImage[i][j] = fil.corner(j, i, 1);
                    } else if (j == n - 1) {
                        newImage[i][j] = fil.corner(j, i, 2);
                    } else {
                        newImage[i][j] = fil.edge(j, i, 1);
                    }
                } else if (i == m - 1) {
                    if (j == 0) {
                        newImage[i][j] = fil.corner(j, i, 4);
                    } else if (j == n - 1) {
                        newImage[i][j] = fil.corner(j, i, 3);
                    } else {
                        newImage[i][j] = fil.edge(j, i, 3);
                    }
                } else if (j == 0) {
                    newImage[i][j] = fil.edge(j, i, 4);
                } else if (j == n - 1) {
                    newImage[i][j] = fil.edge(j, i, 2);
                } else {
                    newImage[i][j] = fil.center(j, i);
                }        
                
            }
        }
        
        
        // TODO system output the data array (newImage)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", newImage[i][j]);
            }
            System.out.print("\n");
        }       
    }
}
