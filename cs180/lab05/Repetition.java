import java.util.Scanner;
 
/**
 * Lab 5: Repetition
 */
public class Repetition {
 
    /**
     * Program entry point.
     * Your main method will not be tested. You can modify this to help you
     * debug your code.
     * 
     * @param args
     *
     */
    public static void main(String[] args) {
        // Your testing can go here
        // Note that you do not need to create objects of
        // type Repetition. This is because the methods are
        // declared as "static"
        binaryCounter();
        System.out.println("----------------");
        even(100);
        System.out.println("----------------");
        System.out.println(factorial(8));
        System.out.println("----------------");
        vertical("Hello");
        System.out.println("----------------");
        testResults();
        //change as you need
    }
 
 
    /**
     * Print out every letter of the string s, each on its own line.
     * Sample Output for argument "Hello":
     * ===================================
     * H
     * e
     * l
     * l
     * o
     * 
     * @param str input string.
     */
    public static void vertical (String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.printf("%c\n", str.charAt(i));
        }
 
    }
 
 
 
    /**
     * Generate and print out all even numbers from 0 to end (inclusive)
     * on the same line.
     * 
     * output should be formatted exactly like what is between [].
     * [0 2 4 6 8 10 12 14 16 18 20 22 24 26 28 ...]
     *
     * @param end the maximum value to print (could be even or odd)
     */
    public static void even(int end) {
        int even_num = 0; //this variable should hold the even numbers found
        for (int i = 0; i < Math.floor(end / 2); i++) {
            System.out.printf("%d ", even_num);
            even_num += 2;
        }
        System.out.printf("%d", even_num);
 
    }
 
 
    /**
     * Calculate the value of n!. The value of factorials can be quite large,
     * so use long instead of int to do your calculations.
     * 
     * @param n calculates n!
     * 
     * @return n!
     */
    public static long factorial(int n) {
        long fact = 1;
        if (n > 1) {
            fact = n * factorial(n - 1);
        }
        return fact;
    }
 
 
    /**
     * Print out all 8 digit binary numbers, each on its own line.
     *
     * Hint: Use nested loops that count from 0 to 1; one loop for each digit of
     * the binary string
     *
     * output should look like the following:
     * 00000000
     * 00000001
     * 00000010
     * 00000011
     * ...
     */
    public static void binaryCounter() {
        int limit = 8;
        for (int i = 0; i < Math.pow(2, limit); i++) {
            //i to binary
            int q = i;
            int bin = 0;
            int multiplier = 1;
            int k = 0;
            while (q > 0) {
                k = q % 2;
                bin = bin + k * multiplier;
                q = (q - k) / 2;
                multiplier *= 10; 
            }
            for (int w = 0; w < (limit - ("" + bin).length()); w ++) {
                System.out.print(0);
            }
            System.out.println(bin);
        }
 
    }
 
 
 
    /**
     * Print test result summary.
     * Requirements
     * 
     *  - Print out "Enter scores now:"
     *     (Make sure there is no space after the colon here)
     *  - Takes in scores (ints) from the user until they 
     *     enter a -1.
     *  - After the user enters -1, you will print out the
     *     lowest score, the highest score, and the average 
     *     of the scores (rounded to the floor, or greatest integer not
     *     exceeding the average. Just use integer division).
     *     See the format outlined in the instructions' sample execution.
     * 
     * Hints 
     *  - No arrays are necessary for this method. If you try to use them, you
     *    are doing too much work
     *  - You can calculate the average by maintaining the sum of all the grades
     *    and the count of all the grades and only calculating the average when
     *    the user is done supplying the grades.
     *  - Maintain one variable for each statistic (count, sum, lowest,
     *    highest), updating each of them for each grade entered.
     *  - When printing the output; note that the output statistics are right
     *    justified.
     *  - You can assume all scores range from 0 to 100 (inclusive). 
     *  - To print out, you can use the following lines
     *
     *    System.out.println("=====-----=====-----=====-----=====");
     *    System.out.println("=            Test Results         =");
     *    System.out.printf("= Average: %22d =\n",average);
     *    System.out.printf("= Lowest:  %22d =\n",lowest);
     *    System.out.printf("= Highest: %22d =\n",highest);
     *    System.out.println("=====-----=====-----=====-----=====");
     * 
     * Where 'average', 'lowest', and 'highest' are int variables whose values
     * are the values you computed.
     */
    public static void testResults() {
        System.out.println("Enter scores now:");
        Scanner c = new Scanner(System.in);
        int total = 0;
        int max = 0;
        int min = 100;
        int c_score = 0;
        int count = 0;
        int av = 0;
        do {
            c_score = c.nextInt();
            if (c_score != -1) {
                total += c_score;
                if (c_score < min) {
                    min = c_score;
                }
                if (c_score > max) {
                    max = c_score;
                }
                count++;
            }
        } while (c_score != -1);
        av = total / count; 
        System.out.println("=====-----=====-----=====-----=====");
        System.out.println("=            Test Results         =");
        System.out.printf("= Average: %22d =\n",av);
        System.out.printf("= Lowest:  %22d =\n",m537in);
        System.out.printf("= Highest: %22d =\n",max);
        System.out.println("=====-----=====-----=====-----=====");
 
    }
}