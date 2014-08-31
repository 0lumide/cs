/***************************************************************************
 *
 *  Programmer and Purdue Email Address:
 *  1. oawofeso@purdue.edu
 *
 *  Homework #:05
 *
 *  Academic Integrity Statement:
 *
 *       I have not used source code obtained from
 *       any other unauthorized source, either modified
 *       or unmodified.  Neither have I provided access
 *       to my code to another. The project I am submitting
 *       is my own original work.
 *
 *  Lab Division and Section:0101
 *
 *  Program Description:This program decrypts the data the user enters and returns the decryted word or the corresponding errors
 *
 ***************************************************************************/

#include <stdio.h>

int getInput(int*, int*, int*);
void calcOutput(int*, int*, int*, int*);
void printOutput(char, char, char, int);

int main()
{
  //LOCAL DECLARATION
  int inp_1; //This is the first input entered by the user
  int inp_2; //This is the second input entered by the user
  int inp_3; //This is the third input entered by the user
  int inp_4; //This is the fourth input entered by the user

  //EXECUTABLE STATEMENT
  inp_4 = getInput(&inp_1, &inp_2, &inp_3);
  calcOutput(&inp_1, &inp_2, &inp_3, &inp_4);
  printOutput(inp_1, inp_2, inp_3, inp_4);
  return(0);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInput
*
*     Programmer's Name:Olumide
*
*     Function Return Type:int
*
*     Parameters (list data type, name, and comment one per line):
*       1.int* val_1 //This is the first input entered by the user
*       2.int* val_2 //This is the second input entered by the user
*       3.int* val_3 //This is the third input entered by the user
*
*     Function Description:This function gets input from the user
*
***************************************************************************/

int getInput(int* val_1, int* val_2, int* val_3)
{
  //LOCAL DECLARATION
  int val_4;//This is the fourth input entered by the user
  int count;//This is the variable used to limt the for loop

  //EXECUTABLE STATEMENT
  for (count = 1; count <= 4; count++)
  {
    printf("Enter encrypted value #%d: ", count);
    switch(count)
    {
      case 1: scanf("%d", val_1);
              break;
      case 2: scanf("%d", val_2);
              break;
      case 3: scanf("%d", val_3);
              break;
      default: scanf("%d", &val_4);
    }
  }
  return(val_4);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function: calcOutput
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int* val_1 //This holds the first interger value entered by the user
*       2.int* val_2 //This holds the second interger value entered by the user
*       3.int* val_3 //This holds the third interger value entered by the user
*       4.int* val_4 //This holds the fourth interger value entered by the user
*
*     Function Description:This function decrypts the given inputs and determines whether the input is valid or not
*
***************************************************************************/

void calcOutput(int* val_1, int* val_2, int* val_3, int* val_4)
{
  //LOCAL DECLARATION
  int letnum_1; //This holds twice the char value of the first character
  int letnum_2; //This holds twice the char value of the second character
  int letnum_3; //This holds twice the char value of the third character

  //EXECUTABLE STATEMENT
  letnum_1 = *val_3 + *val_1;
  letnum_2 = *val_3 - *val_1;
  letnum_3 = *val_4 - *val_2;
  if ((letnum_1 % 2 == 0) && (letnum_2 % 2 == 0) && (letnum_3 % 2 == 0))
  {
    if ((((letnum_1 / 2 >= 48) && (letnum_1 / 2 < 58)) || ((letnum_1 / 2 >= 65) && (letnum_1 / 2 < 91)) || ((letnum_1 / 2 >= 97) && (letnum_1 / 2 < 123))) && (((letnum_2 / 2 >= 48) && (letnum_2 / 2 < 58)) || ((letnum_2 / 2 >= 65) && (letnum_2 / 2 < 91)) || ((letnum_2 / 2 >= 97) && (letnum_2 / 2 < 123))) && (((letnum_3 / 2 >= 48) && (letnum_3 / 2 < 58)) || ((letnum_3 / 2 >= 65) && (letnum_3 / 2 < 91)) || ((letnum_3 / 2 >= 97) && (letnum_3 / 2 < 123))))
    {
      *val_1 = letnum_1 / 2;
      *val_2 = letnum_2 / 2;
      *val_3 = letnum_3 / 2;
    }
    else
    {
      *val_4 = -1;
    }
  }
  else 
  {
    *val_4 = -2;
  }
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:printOutput
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.char val_1 //This holds the char value of the first character
*       2.char val_2 //This holds the char value of the second character
*       3.char val_3 //This holds the char value of the third character
*       4.int val_4 //This holds the number that tells the function whether to print an error message or to print the decripted message
*
*     Function Description:This function prints the decrypted words or the relevant error message
*
***************************************************************************/

void printOutput(char val_1, char val_2, char val_3, int val_4)
{
  switch(val_4)
  {
    case -1: printf("\nOne or more values does not represent an alphanumeric character!");
             break;
    case -2: printf("\nUnable to calculate integer values.");
             break;
    default: printf("\nDecrypted characters: %c %c %c", val_1, val_2, val_3);
  }
  printf("\n\n");
}
