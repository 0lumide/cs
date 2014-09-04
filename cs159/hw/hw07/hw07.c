
/***************************************************************************
 *
 *  Programmer and Purdue Email Address:
 *  1. Olumide Awofeso oawofeso@purdue.edu
 *
 *  Homework #:7
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
 *  Program Description:This program gets a set of numbers from the user and nearly sorts them in ascending order
 *
 ***************************************************************************/
#include <stdio.h>

#define SIZE 25
int getInput(int[]);
void SortData(int[], int);
void PrintOut(int[], int[], int);

int main()
{
  //LOCAL DECLARATION
  int input[SIZE] = {0};//This is an array of 25 that holds the users input
  int output[SIZE] = {0};//This is an array of 25 that holds the nearly sorted data
  int set_size; //This is the variable that holds the amount of input entered by the user
  int i; //for loop control variable

  //EXECUTABLE STATEMENT
  set_size = getInput(input);

  for (i = 0; i < set_size; i++)
  {
    output[i] = input[i];
  }

  SortData(output, set_size);
  PrintOut(input, output, set_size);

  return(0);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInput
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type: int
*
*     Parameters (list data type, name, and comment one per line):
*       1.int input[] //This is the array that will hold the data entered by the user
*       
*       
*
*     Function Description:This function gets input from the user
*
***************************************************************************/


int getInput(int input[])
{
  int i;
  int count = 0;
  int num = 0;

  for (i = 0; i < SIZE; i++)
  {
    printf("Enter data #%d or -1 to exit: ", i+1);
    scanf("%d", &num);

    if (num == -1)
    {
      i = SIZE;
    }
    else
    {
      input[i] = num;
      count++;
    }

  }
  return(count);
}



/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:
 *
 *     Programmer's Name:Olumide Awofeso, Jordan roark,Izzat Ghuneim
        //Part of this function is from my teams lab submission
 *
 *     Function Return Type:void
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.int array[] //this is a duplicate of the data the user entered that is to be nearly sorted
 *       2.int set_size //this is the amount of data that the user entered
 *       
 *
 *     Function Description:This function nearly sorts the array "array"
 *
 ***************************************************************************/

void SortData(int array[], int set_size)
{
  //LOCAL DECLARATION
  int stop = 3;// variable that stops the sort process when sorting is complete
  int i; // for loop variable
  int temp; // variable used for temporarily holding values while switching
  int q = set_size - 1;// this controls the while loop
  int w =1; //This also controls the while loop

  //EXECUTABLE STATEMENT
  while (stop != 0)
  {
    stop = 0;
    for (i = 0; i < set_size -  1; i++)
    {
      if (array[i+1] < array[i])
      {
        temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
        stop = 2;
      }
    }
  }

  while ((q > 0) && (w != 0))
  {
    if (array[q-1] < array[set_size-1])
    {
      temp = array[q];
      array[q] = array[q - 1];
      array[q - 1] = temp;
      w = 0;
    }
    --q;
  }
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:PrintOut
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type: void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int Input[] //This is the original data in the order the user entered them
*       2.int Output[] //This array hold the nearly sorted data
*       3.int set_size //This is the amount of data that the user enters
*
*     Function Description: This function prints the original input and the nearly sorted output
*
***************************************************************************/

void PrintOut(int Input[], int Output[], int set_size)
{
  //LOCAL DECLARATION;
  int i; //for loop control variable

  //EXECUTABLE STATEMENT
  printf("\nOriginal Data: ");
  for(i = 0; i < set_size; i++)
  {
    printf("%d ", Input[i]);
  }

  printf("\nFinal Data: ");
  for(i = 0; i < set_size; i++)
  {
    printf("%d ", Output[i]);
  }
  printf("\n\n");
}

