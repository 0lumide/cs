
/***************************************************************************
*
*  Programmers and Purdue Email Addresses:
*	 1. Ncosselm@purdue.edu
*	 2. saudi@purdue.edu
*	 3. oawofeso@purdue.edu (delete line if no third partner)
*
*  Lab #:03
*
*  Academic Integrity Statement:
*
*       We have not used source code obtained from
*       any other unauthorized source, either modified
*       or unmodified.  Neither have we provided access 
*       to our code to another. The project we are submitting
*       is our own original work.
*
*  Lab Division and Section:0101
*
*  Program Description: Determining taxes owed using user defined functions
*
***************************************************************************/

#include <stdio.h>

// GLOBAL DECLARATIONS - FUNCTION DECLARATIONS

float getIncome();
float calcRate(int);
void displayResults(int,float);
int main()

{
  // LOCAL DECLARATION

  int income; //the original income recieved from main
  float taxRate; //the taxrate recieved from main

  // EXECUTABLE STATEMENTS
  income = getIncome(); // calls the getIncome function
  taxRate = calcRate(income); //calls the calcRate function
  displayResults(income,taxRate);
  return(0);
}

/***************************************************************************
*
*     Function Information
*
*     Name of Function: getIncome
*
*     Programmer's Name: Shadi Audi, Olumide Awofeso, Nick Cosselman
*
*     Function Return Type: float
*
*     Parameters (list data type, name, and comment one per line):
*       1. float income this is the entered income 
*      
*      
*
*     Function Description: Accepts and returns input income from user
*
***************************************************************************/

float getIncome()
{
  // LOCAL DECLARATIONS

  float income; //this is the income to be calculated in further functions
  char dollar_sign; //char value in order to display dollar sign
  
  // EXECUTABLE STATEMENTS

  dollar_sign = 36; 
 
  printf ("\nEnter taxable income: ");
  scanf("%f", &income);
  printf("\nTaxable Income: %c %7.2f\n", dollar_sign, income); 
  return(income);
}

/***************************************************************************
*
*     Function Information
*
*     Name of Function: calcRate
*
*     Programmer's Name: Shadi Audi, Olumide Awofeso, Nick Cosselman
*
*     Function Return Type: float
*
*     Parameters (list data type, name, and comment one per line):
*       1. float income from main
*       
*
*     Function Description: given the input this function will return taxable rate
*
***************************************************************************/

float calcRate(int income)

{

  // LOCAL DECLARATIONS

  int rate; //the base tax rate
  int min_tax; //minimum salary that can be taxed
  char percent_sign; // char input for the percent sign
  float x_rate; //determines if the number is more than minimum taxable income
  float tax_rate; //this is the taxable rate

  // EXECUTABLE STATEMENTS

  min_tax = 65000;
  percent_sign = 37;
  x_rate = ((income/min_tax) - ((income-min_tax)/min_tax));
  rate = ((income-min_tax)/(7500))*x_rate;
  tax_rate = ((x_rate)*7)+(rate *.5);
   
  printf("\nTax Rate : %4.3f%c\n", tax_rate, percent_sign);
  return(tax_rate);
}

/***************************************************************************
*
*     Function Information
*
*     Name of Function: displayResults
*
*     Programmer's Name: Shadi Audi, Nick Cosselman, Olumide Awofeso
*
*     Function Return Type:
*
*     Parameters (list data type, name, and comment one per line):
*       1. float income from main 
*       2. float taxRate from main
*       
*
*     Function Description: given the income and taxable rate this function will dispay the taxes owed
*
***************************************************************************/

void displayResults(int income, float taxRate)
{
  // LOCAL DECLARATIONS
  
  float tax_owed; //the taxes owed due to income and calculated taxRate
  char dollar_sign; //char input flor dollarsign

  // EXECUTABLE STATEMENTS

  dollar_sign = 36; //char value for the dollarsign
  tax_owed = (income*taxRate)/100;
  printf("Tax Owed : %c %0.2f\n\n", dollar_sign, tax_owed); 
}



