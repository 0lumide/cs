import java.util.Scanner;
/**
 * Purdue CS 180 Spring 2014
 * Lab 3
 * 
 * Sample implementation of StringManipulator
 * 
 * @author Olumide Awofeso <oawofeso@purdue.edu>
 */
 
/**
 * Assign username and email id, given the fullname of the person and the domain.
 */
 
 
public class StringManipulator {
 
  /**
   * Make a username out of a person's fullname (first and last only, separated by
   * a space).
   *
   * This method accepts a String as an argument. This String represents a
   * person's fullname, e.g. "John Doe" or "Ann Smith".
   *
   * The fullname is passed as an argument to this method from main.
   * 
   * Your method should return the entire username in lowercase regardless of
   * the case of the input. For example, the input "JOHN DOE" should return
   * "jdoe" and not "JDOE".
   * 
   * HINT: If the name is "John Doe", get first letter with substring method
   *       ("J"), get substring after the space until the end of the string 
   *       ("Doe"), concatenate them together ("JDoe"), and make them in 
   *       lowercase ("jdoe").
   * 
   * @param name
   *            fullname, this parameter contains the person's first and last name separated by a space
   * @return username
   *            username generated by this method.
   */
 
  public String makeUsername(String name) {
    // YOUR CODE GOES HERE.
    String username;
    username = name.charAt(0) + name.substring(name.indexOf(' ')+1);
    username = username.toLowerCase();
    return(username);
  }
 
 
  /**
   * Make an email address out of the username and the domain
   *
   * This method takes the username and an Internet domain as arguments, 
   * e.g. "jdoe" and "purdue.edu" and returns an email address, 
   * e.g. "jdoe@purdue.edu".
   *
   * The username and the domain are passed as arguments to this method from main.
   * The email address must be in all lowercase, regardless of the case
   * of the domain. For example, the input ("jdoe", "Purdue.EDU")
   * should return "jdoe@purdue.edu", not "jdoe@Purdue.EDU". 
   * 
   * @param name
   *            username returned by the makeUsername method
   * @param domain
   *            Internet domain name (e.g. purdue.edu)
   * @return email
   *            Email address generated
   */
 
  public String makeEmail(String name, String domain) {
    // YOUR CODE GOES HERE. 
    String email;
    domain = domain.toLowerCase();
    email = name+"@"+domain;
    return(email);
  }  
 
 
    /**
   * Writing the main() method
   *
   * This method accepts two input strings from the user, which are: fullname and domain
   * fullname: This is the full name of the person, first name followed by last name
   * domain: Name of the internet domain to be used to create the email id
   *
   * The main method calls the makeUsername and makeEmail methods one after another 
   * with appropriate parameters.
   * 
   * The values returned by these methods are printed onto the console.
   * 
   * Look at the sample output below and use appropriate print statements within main
   */
 
  public static void main(String[] args) {
    //Get users Name
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the Full Name of the person. First Name followed by Last Name.");
    String name = s.nextLine();
    System.out.println("Enter the domain");
    String domain = s.nextLine();
    
    //Generated the username and email address
    StringManipulator strman = new StringManipulator(); 
    String username; //This holds the username generated by makeUsername
    String email; //This holds the email generated by the method make Email
    username = strman.makeUsername(name);
    email = strman.makeEmail(username, domain);
    System.out.println("The user name for the person is: "+username);
    System.out.println("The email id for the person is: "+email);
    }
}