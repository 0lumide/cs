import java.util.Scanner;
/**
* CS180 - Lab 02
* This class calculates the length of the hypotenus, wehn given the length of the other two sides.
* @author Olumide, <oawofeso@purdue.edu>
*/

public class Pythagoras {
    public static void main(String[] args) {
        Pythagoras p = new Pythagoras();
        Scanner scanner = new Scanner(System.in); //creates a scanner object
        //Prompts user for input:
        System.out.println("Enter side 'a': ");
        //Reads the users input
        int a = scanner.nextInt(); //reads an integer from the standard input
        //Prompts user for input:
        System.out.println("Enter side 'b': ");
        int b = scanner.nextInt(); //reads an inteher from the standard input
        double h;
        h = p.getHypotenuse(a,b);
        System.out.println("Hypotenuse: " + h);

    }
    public double getHypotenuse(int a, int b) {
        double h;
        h = Math.sqrt(a*a+b*b); //calculates the length of the hypotenus
        return h; //returns the calculated value
    }
}

