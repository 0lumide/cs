/**
 * CS180 - Lab 04 - Investor.java
 *
 * This program calculates the interst rate and savings after a period of time for customers based on the input given
 *
 * @author Olumide Awofeso, <oawofesp@purdue.edu>
 *
 * @lab (L14)
 *
 * @date (2/7/2014)
 */

//Import Scanner for taking input from the user

import java.util.Scanner;

public class Investor {
    
    //Hint: Declare your instance variables here. 
    //Don't forget to comment each variable declaration as
    //specified in the CS18000 Coding Standards. 
    
    /* 
     * Instance variables
     * name: Name of investor
     * customer: customer ID of investor
     * age: age in years
     * balance: Investment balance=> [Math.random()*10000 in range (0,10000)]
     */
    String name;
    String customerID;
    int age;
    double balance;
    int rate;
    
    
    
    
    /*
     * Constructor for Investor:
     * 
     * Initialize instance variables
     * name, customerID and age passed by user input. 
     * *** customerID starting with 'E' implies customer is an employee 
     */ 
    public Investor (String name, String customerID, int age)  {
        //write your code here to initialize your instance variables
        this.name = name;
        this.customerID = customerID;
        this.age = age;
        this.balance=Math.random()*10000;
        assignRate();
    }
    
    
    /* Method: investorGroup()
     * 
     * @return: returns a character encoding the investor group
     * Investor group is decided based on two variables: customerID and balance
     * balance >= 5000 is gold category
     * balance < 5000 is silver category
     * Investor is an employee, if customerID begins with 'E'
     * 
     * Groups are encoded as follows:
     * a- Employee in gold Category
     * b- Employee in silver Category
     * c- Non-Employee in gold category
     * d- Non-Employee in silver category
     * 
     */
    public char investorGroup() {
        //write code to implement this method here
        char group;
        if(this.customerID.charAt(0)=='E'){
            if(this.balance >= 5000){
                group = 'a';
            }else{
                group = 'b';
            }        
        }else{
            if(this.balance >= 5000){
                group = 'c';
            }else{
                group = 'd';
            }
        }
        
        
        return group; //you will replace the x with your return value inside the nested if-else loop
    }
    
    
    /*
     * Method assignRate:
     * Rate of interest is assigned to investor based on investor group
     * Group a- 4% per year
     * Group b- 3% per year
     * Group c- 2% per year
     * Group d- 1% per year
     */
    public void assignRate(){
        
        char group = investorGroup();
        // Use the group variable to determine the rate of interest using selection 
        // write the code to implement this method here
        switch(group){
            case 'a': this.rate = 4; break;
            case 'b': this.rate = 3; break;
            case 'c': this.rate = 2; break;
            case 'd': this.rate = 1; break;
        }
    }
    
    
    /* Method calculateInterest():
     * @period: investment period
     * simple interest is calculated as balance*rate*period
     */ 
    public double calculateInterest (int period) {
        
        return (balance*rate*period/100);
    }
    
    public static void main(String[] args) {
        // write the code to implement this method here
        Scanner s = new Scanner(System.in);
        System.out.println("Enter customer name:");
        String name = s.nextLine();
        System.out.println("Enter customer ID:");
        String customerID = s.nextLine();
        System.out.println("Enter customer age:");
        int age = s.nextInt();
        Investor investor = new Investor(name, customerID, age);
        System.out.println("Enter period of investment:");
        int period = s.nextInt();
        double interest = investor.calculateInterest(period);
        
        //Begin output
        System.out.println("Investor Information");
        System.out.printf("Name:%s\n", name);
        System.out.printf("Customer ID:%s\n", customerID);
        System.out.printf("Age:%d\n", age);
        System.out.printf("Account Balance:%f\n", investor.balance);
        System.out.printf("Rate of interst per year:%d\n", investor.rate);
        System.out.printf("Innterest earned in %d years:%f\n", period, interest);
    }  
    
}    