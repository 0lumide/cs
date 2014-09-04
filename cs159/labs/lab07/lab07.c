
/***************************************************************************
 *
 *  Programmers and Purdue Email Addresses:
 *	 1. dwisser@purdue.edu
 *	 2. oawofeso@purdue.edu
 *	 3. jcourtn@purdue.edu (delete line if no third partner)
 *
 *  Lab #:07
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
 *  Program Description: This program finds a set of two prime numbers that when added together gives a value that is double the input of the user
 *
 ***************************************************************************/

#include <stdio.h>

int getInput(void);
int calcPrime(int);
void printOut(int, int, int* );

int main()
{
  //LOCAL DECLARATION
  int input; //This is the number entered by the user received from getInput
  int num; //This is the variable to be used in the for loop to find the firt prime number
  int count; //This is the vaue recieved from calcPrime function
  int sec_num; //This is the second number that fulfils the condition of adding to the first number to give double the input
  int count2 = 1; //This counts the amount of pairs of prime numbers that have been found

  //EXECUTABLE STATEMENTS
  input = getInput();
  for (num = 1; num <= input; num++)
  {
    count = calcPrime(num);
    if (count == 2)
    {
      sec_num = input * 2 - num;
      count = calcPrime(sec_num);
      if (count == 2)
      {
        printOut(num, sec_num, & count2);
        count2++;
      }
    }
  }
  if (count2 == 1)
  {
    printf("\nNo prime pairs found.");
  }
  printf("\n\n");
  return(0);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInput
*
*     Programmer's Name:Olumide Awofeso
*                       John Courtney
*                       Drake Wisser
*
*     Function Return Type:int
*
*     Parameters (list data type, name, and comment one per line):
*       1.int num //This is the input entered by the user
*
*
*     Function Description:This function gets the input for the program
*
***************************************************************************/

int getInput()
{
  //LOCAL DECLARATION
  int num; //This is the value input by the user

  //EXECUTABLE STATEMENT
  do
  {
    printf("\nEnter quantity: ");
    scanf("%d", &num);
    if (num < 1)
    {
      printf("\nInvalid Input!  Enter a positive integer value!\n");
    }
  }while (num < 1);
  return(num);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:printOut
*
*     Programmer's Name:Olumide Awofeso
*                       John Courtney
*                       Drake Wisser
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int first //This is the first number of the pair of prime numbers to be printed
*       2.int second //This is the second number of the pair of prime numbers to be printed
*       3.int* count //This is the number that tells the function when to print a new line
*
*     Function Description:This function prints the output in the specified format.
*
***************************************************************************/

void printOut(int first, int second, int* count)
{
  
  //EXECUTABLE STATEMENTS
  if (*count == 1)
  {
    printf("Pairs found: ");
  }
  printf(" [%d, %d]", first, second);
  if ((*count % 5) == 0)
  {
    printf("\n");
  }
}




/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcPrime
*
*     Programmer's Name:Olumide Awofeso
*                       John Courtney
*                       Drake Wisser
*
*     Function Return Type:int
*
*     Parameters (list data type, name, and comment one per line):
*       1.int num //This is the number that is to be checked if it is prime
*       2.int count //This is the value returned to main that signifies whether a number is prime
*
*     Function Description:This function determines if the number passed to it is prime
*
***************************************************************************/

int calcPrime(int num)
{
  //LOCAL DECLARATION
  int count = 0; //This counts the amount of factors a number has
  int test = 1; //This is the number that divides the input number to determine if it is prime

  //EXECUTABLE STATEMENT
  do 
  {
    if ((num % test) == 0)
    {
      count++;
    }
    test++;
  }while (test <= num);
  return(count);
}

