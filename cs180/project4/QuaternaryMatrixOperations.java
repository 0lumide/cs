/**
 * Project 4 -- Operations on Quaternary Numbers
 * Creator: Varun Vasudevan, Email: vasudeva@purdue.edu
 * 
 * This program performs addition and multiplication on matrices containing quaternary numbers
 * 
 * 
 * TODO: Replace the usernames below with your Purdue usernames. 
 * During grading, Web-CAT will automatically detect team members 
 * from these tags, so you will not need to click "Choose Partners" 
 * when submitting.
 * 
 * @author Olumide Awofeso, oawofeso@purdue.edu
 * @author partner2username
 *
 * @date 2/23/2014
 */
import java.util.*;

public class QuaternaryMatrixOperations {
    
    //-------------------- Do not modify this section of the code--------------------------------------------//
    // addition look-up array (table)
    int[][] addTable = {{0, 1, 2, 3}, {1, 0, 3, 2}, {2, 3, 0, 1}, {3, 2, 1, 0}}; 
    // multiplication look-up array (table)
    int[][] mulTable = {{0, 0, 0, 0}, {0, 1, 2, 3}, {0, 2, 3, 1}, {0, 3, 1, 2}}; 
    
    int size; // We are dealing with square matrices. Size = # of rows = # of columns
    int [][] a; // square matrix
    int [][] b; // square matrix
    
    //------------------------------------------------------------------------------------------------------//
    
    /**
     * Constructor without any parameters
     * Initialize matrix a to {{3, 2}, {1, 3}}
     * Initialize matrix b to {{2, 1}, {3, 1}}
     * Initialize size to the length of a or b
     */
    
    public QuaternaryMatrixOperations() {
        // initialize a
        this.a = new int[][]{{3, 2}, {1, 3}};
        // initialize b
        this.b = new int[][]{{2, 1}, {3, 1}};
        // initialize size
        this.size = 2;

    }
    
    /**
     * Constructor with one parameter
     * Initialize matrix a to inpMatrix
     * Initialize matrix b to inpMatrix
     * Initialize size to the length of a or b
     * @param inpMatrix. Type int[][]. Contains the matrix generated using the input binary string
     */
    public QuaternaryMatrixOperations(int [][] inpMatrix) {
        // initialize a to inpMatrix
        this.a = inpMatrix;
        // initialize b to inpMatrix
        this.b = inpMatrix;
        // initialize size
        this.size = inpMatrix.length;

    }
    
    /**
     * Methods called: performOperation(), getInput(), printMatrix()
     * @param args
     */
    public static void main(String[] args) {
        int[][] defaultResultArray;
        int[][] inputArray;
        int[][] inputResultArray;
        System.out.println("Working with Default Matrices");
        // Create a new object by invoking the constructor without any parameters
        // Call performOperation()
        // Print the result
        QuaternaryMatrixOperations objectOne = new QuaternaryMatrixOperations();
        defaultResultArray = objectOne.performOperation();
        objectOne.printMatrix(defaultResultArray);
        
        
        
        System.out.println("Working with User Input Matrices");
        // generate a matrix by calling getInput()
        inputArray = getInput();
        // Create a new object by invoking the constructor with parameter
        QuaternaryMatrixOperations objectTwo = new QuaternaryMatrixOperations();
        // Call performOperation()
        inputResultArray = objectTwo.performOperation();
        // Print the result
        objectTwo.printMatrix(inputResultArray);
        
        
    }
    
    /**
     * This method is called by the main. 
     * It asks the user to input a '+' or '*' for matrix addition and multiplication respectively
     * Depending on the input calls either matrixAdd() or matrixMultiply()
     * Methods called: matrixAdd(), matrixMultiply()
     * 
     * @return result. Type int[][]. Contains result of the addition or multiplication 
     */
    public int[][] performOperation() {
        // declare and initialize necessary variables
        Scanner s = new Scanner(System.in);
        System.out.print("Enter operation:");
        int[][] outArray = null;
            
        // Take the char input from the user. It is either a + or *
        char inputChar = (s.nextLine()).charAt(0);
        if (inputChar == '*') {
            outArray = matrixMultiply();
        }
        // Based on the operator entered by the user call either matrixAdd() or matrixMultiply()
        else if (inputChar == '+') {
            outArray = matrixAdd();
        }
        return outArray; 
    }
    
    /**
     * This method is called by the main. 
     * This method does the following: 
     * Step1: Asks user to enter the order of the square matrix. Store it into an int variable. 
     * Step2: Computes the value of an int variable lengthOfString using the expression 2*order*order
     * Step3: Asks the user to enter a binary string of length equal to lengthOfString. 
     * If the entered string has a different length then prompt the user to enter the string again
     * Step4: Splits the binary string into substrings of two characters. 
     * For each substring find the corresponding quaternary digit
     * Example: binaryString = "10110001". Splitting it will result in substrings "10", "11", "00" and "01".
     * Each of them is then converted into int, resulting in 10, 11, 0, 1. 
     * These values are then converted to quaternary digits using selection statements
     * This will result in 2, 3, 0 and 1 respectively.
     * Step5: Store the resulting quaternary digits in a matrix with dimensions order x order. 
     * While storing into the matrix first fill the __zeroth row__ of the matrix and then the next and so on.
     * 
     * @return inpMatrix. Type int[][]. Contains the generated matrix.
     */
    
    public static int[][] getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the order of the square matrix. Example: enter 3 for a 3x3 matrix");
        int order = input.nextInt();
        int lengthOfString = 2 * order * order;
        String inputString = null;
        //int inputInt = 0;
        int[][] inputMatrix;
        int count = 0;
        //Scanner s = new Scanner(System.in);
        do {
            System.out.printf("Enter a binary string of length %d:", lengthOfString);
            // declare and initialize necessary variables
            //int[] binaryMatrix = new int[order * order];
            inputMatrix = new int[order][order];
            // Take the binary string from the user by printing the statement
            inputString = input.nextLine();
            //inputString = inputInt + "";
        } while (inputString.length() != lengthOfString);
        // "Enter a binary string of length __" as shown in the sample output
                
        
        // Write code for Step 4 mentioned in the documentation comment for this method
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                switch (inputString.substring(2 * count, 2 * (count + 1))) {
                    case "00":
                        inputMatrix[i][j] = 0;
                        break;
                    case "01":
                        inputMatrix[i][j] = 1;
                        break;
                    case "10":
                        inputMatrix[i][j] = 2;
                        break;
                    case "11":
                        inputMatrix[i][j] = 3;
                        break;
                }
                count++;
            }
        }
        // Write code for Step 5 mentioned in the documentation comment for this method
        
        
        
        // return statement;
        return inputMatrix;
        
        
       
    }
    
    /**
     * This method is called by performOperation(). 
     * It adds each element of matrix a with the corresponding element in the transpose of matrix b
     * Example: To compute 3 + 2, you will fetch the value in the cell corresponding to third row 
     * and second column in addTable array, i.e. addTable[3][2]. The value in that location is 1.
     * Therefore, you get 3 + 2 = 1
     * 
     * @return addResult. Type int [][]. Contains result of addition.        
     */
    public int[][] matrixAdd() {
        // declare and initialize necessary variables
        int[][] returnValue = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                returnValue[i][j] = this.addTable[this.a[i][j]][this.b[j][i]];
            }
        }
        return returnValue;
        
    }
    
    /**
     * This method is called by performOperation(). 
     * It multiplies matrix a with matrix b. 
     * If you are not familiar with matrix multiplication then visit -
     * http://www.mathsisfun.com/algebra/matrix-multiplying.html
     * 
     * @return multiplyResult. Type int [][]. Contains result of multiplication.        
     */
    public int[][] matrixMultiply() {
        // declare and initialize necessary variables
        int[][] returnValue = new int[this.size][this.size];
        int first;
        int second;
        int addedValue;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                addedValue = mulTable[this.a[i][0]][this.b[0][j]];
                for (int k = 1; k < this.size; k++) {
                    first = this.a[i][k];
                    second = this.b[k][j]; 
                    addedValue = addTable[addedValue][mulTable[first][second]];
                }
                returnValue[i][j] = addedValue;
            }
        }
        return returnValue;
        
    }
    
    /**
     * This method is called by main(). 
     * It prints the result matrix in row column format
     * 
     * @params result. Type int[][]. Contains the matrix to be printed        
     */
    
    public void printMatrix(int[][] result) {
        System.out.println("Result is:");
        // Write the code to print the matrix
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.printf(" %d", result[i][j]);
            }
            System.out.print("\n");
        }
    }
}
