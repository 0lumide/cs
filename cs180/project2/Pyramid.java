/**
 * Project 2 -- Pyramid
 * 
 * This program generates and displays a pyramid of a given height. 
 * The height is given by the user.
   
 * @author Olumide Awofeso
 * @lab L14 David Runyan
 * @date 2/11/2014
 */

import java.util.Scanner;

/**
 * The height of the pyramid is provided as input from the user.
 * The height should be a non-negative integer no greater than 24
 * 
 */

public class Pyramid {

/*
 * Get the height of Pyramid from user input
 * re-prompt the user if the height is not valid (you need a loop to do so...)
 * (think about which kind of loop is best to do that...)
 */

    public int getHeight() {
    // insert your code here
        int height = -1;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Please enter height:\n");
            if (s.hasNextInt()) {
                height = s.nextInt();
            }
        } while((height < 0) || (height > 24));    
        return height;
    }
    
    
/*
 * main() Method
 * the main() Method must do the following:
 * create a Pyramid object
 * call getHeight method to get the height of the pyramid from user
 * Generates the desired Pyramid with the given height using loops
 *
 * @param args can be ignored.
 */

    public static void main(String[] args) {
      // TODO create a Pyramid object 
      // TODO input Height for the pyramid
      // TODO call the getHeight method to get the height
      // TODO generate the pyramid with the height given by the user

      // Note: be sure to adhere to the output format described above
        Pyramid py = new Pyramid();
        int h = py.getHeight();
        for (int i = 1; i <= h; i++) {
            for (int j = 0; j < (h - i); j++) {
                System.out.printf(" ");
            }
            for (int k = 0; k < 2 * i; k++) {
                System.out.printf("#");
            }
            System.out.printf("\n");
        }
    }
    
}
