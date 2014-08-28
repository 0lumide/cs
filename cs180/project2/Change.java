/**
 * Project 2 -- Change
 * 
 * This program asks the user the amount to be changed
 * Then it calculates the minimum number of coins needed to change that amount
 * Assume that the only coins available are quarters, dimes, nickles, and pennies

 * @author Olumide Awofeso
 * @lab L14
 * @date 02/11/2014
 */

import java.util.Scanner;
/**
 * The Amount owed by the cashier is provided as input from the user.
 * The amount owed should be a non-negative integer
 * Calculates the minimum no, of coins based on the greedy approach
 */

public class Change {

/*
 * Ask the user the amount of change owed
 * re-prompts user if the input is not valid (need certain loop to do so)
 * Keep asking if the amount is invalid (e.g less than 0)
 */

    public int getAmount () {
    //insert your code here
        int f;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Hi there! How much change is owed?");
            f = (int)Math.round(100 * s.nextDouble());
            System.out.print(f);
        } while (f < 0);
        return f;
    }

/*
 * main () Method
 * the main() Method must do the following:
 * create an object of type Change
 * call getAmount method to get the amount owed by the cashier
 * calculate the minimum no. of coins
 * 
 * @param args can be ignored.
 */

    public static void main(String[] args) {
        // TODO create a Change object
        // TODO call getAmount to get the amount owed
        // TODO Calculate the minimum no. of coins
        // TODO Print the result
        // Note: be sure to adhere to the described input and output format
        Change chng = new Change();
        int coins = chng.getAmount();
        //int coins = change;
        int count = 0;
        while (coins >= 25) {
            count++;
            coins -= 25;
        }
        while (coins >= 10) {
            count++;
            coins -= 10;
        }
        while (coins >= 5) {
            count++;
            coins -= 5;
        }
        while (coins > 0) {
            count++;
            coins -= 1;
        }
        if (count != 0) {
            System.out.printf("The number of coins is: %d\n", count);
        }
    }
}
