import java.util.Random;
import java.util.Scanner;

public class FunArray {
    
    /*
     * Generates the two dimensional array.
     * 
     * - Create a new two dimensional array of integers with dimensions **rows by cols**
     * - Use Random class to generate integers from 0 to 50 as array values
     * - Fill in the array in row -major order (Top Left to Bottom Right)
     * - Return the two dimensional integer array at the end.
     * @parameters   int rows, int cols (they are all user input)
     * @return int[][]         random number matrix
     */
    
    public static int[][] genArray(int rows, int cols) {
        // INSERT CODE HERE
        Random rnd = new Random();
        int[][] randArray = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //randArray[i][j] = Math.round(Math.round(50*Math.random()));
                randArray[i][j] = rnd.nextInt(50);
                //System.out.printf("%d \t", randArray[i][j]);
            }
        }
        return randArray; // CHANGE THIS TO RETURN YOUR OWN ARRAY.
    }
    
    /*
     * Copy a 2D array into a 1D array
     *
     * - Go through the entire 2D array, 
     * - from Bottom Right to Top Left(pay attention here) 
     * - Store each number in the one dimensional array in its index order
     * - Return your one dimensional array at the end.
     *
     * @param int[][] list, a 2D array
     * @return a one dimensional array
     */
    public static int[] twoToOne(int[][] list) {
        // INSERT CODE HERE
        int listColLength = list[0].length;
        int listRowLength = list.length;
        int[] oneArray = new int[listRowLength * listColLength];
        int count = 0;
        for (int i = listRowLength - 1; i >= 0; i--) {
            for (int j = listColLength - 1; j >= 0; j--) {
                oneArray[count] = list[i][j];
                count++;
            }
        }
        return oneArray; // CHANGE THIS TO RETURN YOUR OWN ARRAY.
    }
    
    /*
     * Bubble Sort
     * 
     * - Sort the list in ascending order using "Bubble Sort"
     * - means after sorting the biggest number 
     * - will be at the end of the list
     *
     * @param int[] list, a 1D array
     * 
     */
    
    public static void bubbleSort(int[] list) {     
        // INSERT CODE HERE
        boolean stopSort;
        int temp = 0;
        do {
            stopSort = false;
            for (int i = 1; i < list.length; i++) {
                if (list[i - 1] > list[i]) {
                    temp = list[i];
                    list[i] = list[i - 1];
                    list[i - 1] = temp;
                    stopSort = true;
                }
            }
        } while(stopSort);
        printOneDArray(list);
        
    }
    
    /* 
     * Prints the one dimensional array
     *
     * - print the items in the array 
     * - separates each number by a space
     * 
     * @param int[] list A one-dimensional array of integers
     */
    
    public static void printOneDArray(int[] list) { 
        // INSERT CODE HERE
        int limit = list.length;
        //System.out.print(list[0]);
        for (int i = 0; i < limit; i++) {
            System.out.printf("%d ", list[i]);
        }
        System.out.print("\n");
    }
    
    /* 
     * Prints the Two dimensional array
     *
     * - print the items in the 2D array 
     * - From "Top Left to Bottom Right"
     * - separates each number by a space
     * - make sure it goes to a new line when you reach the end of a row
     * - follow the printing format specified in the handout for each number 
     * 
     * @param int[][] list A two-dimensional array of integers
     */
    
    public static void printTwoDArray(int[][] list) { 
        // INSERT CODE HERE
        int limit = 0;
        for (int i = 0; i < list.length; i++) {
            limit = list[i].length;
            //System.out.print(list[i][0]);
            for (int j = 0; j < limit; j++) {
                System.out.printf("%2d ", list[i][j]);    
            }
            System.out.print("\n");
        }
    }
    
    /*
     * Main Method
     *
     * - You don't need to create a FunArray object
     * - because all the methods are Static
     * - create a Scanner object
     * - prompt the user for the number of rows and cols(rows >= 1 and cols >= 1)
     * - declare a Two-Dimensional Array 
     * - use genArray method to generate the 2D array 
     * - assign the generated array to the 2D array you just created 
     * - print out the 2D array by calling printTwoDArray method
     * - declare a one-dimensional array
     * - call twoToOne method to transfer the generated 2D array into
     * - a one-dimentional array and assign it to the array you just created
     * - print out the 1D array using printOneDArray method
     * - use bubbleSort method to sort the 1D array
     * - print out the sorted array using printOneDArray method
     *
     */
    
    public static void main(String[] args) {
        // INSERT YOUR CODE HERE
        // No need to create a FunArray object
        Scanner s = new Scanner(System.in);
        
        System.out.println("Please enter rows:");
        int rows = s.nextInt();
        System.out.println("Please enter cols:");
        int cols = s.nextInt();
        int[][] genArray = genArray(rows, cols);
        int[] oneArray = twoToOne(genArray);
        
        System.out.println("The generated two dimensional array: ");
        // TODO print out the generated 2D array
        printTwoDArray(genArray);
        
        System.out.println("The list before bubble sort: ");
        // TODO print out the 1D array before sorting
        printOneDArray(oneArray);
        
        System.out.println("The list after bubble sort: ");
        // TODO print out the 1D array after sorting
        bubbleSort(oneArray);
    }
    
}

