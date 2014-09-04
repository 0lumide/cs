/***************************************************************************
 *
 *  Programmers and Purdue Email Addresses:
 *	 1. roark2@purdue.edu
 *	 2. oawofeso@purdue.edu
 *	 3. ighuneim@purdue.edu 
 *
 *  Lab #:11
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
 *  Program Description: This program goes through a series of numbers to find out the highest average that can be gotten from it's subsets
 *
 ***************************************************************************/

#include<stdio.h>
#include<math.h>

#define SIZE 20

int getInput(int[]);
void SortData(int[], int);

int main()
{
  // LOCAL DECLARATION

  int i;//for loop variable
  int array[SIZE];// this is the array that holds the users input
  float temp2; //this variable is used in calculating the average of subsets
  int set_size; //this holds the number of data to be expected as entered by the user

  //EXECUTABLE STATEMENT

  set_size = getInput(array);
  SortData(array, set_size);

/*  temp2 = (array[0] + array[1]);
  temp2 = temp2 / 2;*/

  for (i = 0; i < set_size; i++)
  {
    printf("\n%d", array[i]);
  }
  printf("\n\n\n");

  return(0);
}



/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:getInput
 *
 *      Programmer's Name:Jordan Roark
                          Izzat Ghuneim
                          Olumide Awofeso
 *
 *     Function Return Type: int
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.int array[];// this is the array that holds the users input
 * 
 *
 *     Function Description: This function gets the amount of input specified by the user from the user
 *
 ***************************************************************************/

int getInput(int array[])
{
  //LOCAL DECLARATION
  int set_size; //This holds the number of data to be expected
  int i; //This is the for loop control variable


  //EXECUTABLE STATEMENTS
  do
  {
    printf("\nEnter set size: ");
    scanf("%d", &set_size);
    if ((set_size > 20) || (set_size < 2))
    {
      printf("\nError! Enter a value in the 2 to 20 range!\n");
    }
  }while ((set_size > 20) || (set_size < 2));

  for (i = 0; i < set_size; i++)
  {
    printf("Enter data #%d: ", i + 1);
    scanf("%d", &array[i]);
  }

  return(set_size);
}




/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:SortData
 *
 *     Programmer's Name:Jordan Roark
                         Izzat Ghuneim
                         Olumide Awofeso
 *
 *     Function Return Type:void
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.int set_size; //this holds the number of data to be expected as entered by the user
 *       2.int array[];// this is the array that holds the users input
 * 
 *
 *     Function Description: This function sorts the data gotten from main in ascending order of magnitude
 *
 ***************************************************************************/

void SortData(int array[], int set_size)
{
  //LOCAL DECLARATION
  int stop = 3;// variable that stops the sort process when sorting is complete
  int i; // for loop variable
  int temp; // variable used for temporarily holding values while switching


  //EXECUTABLE STATEMENT
  while (stop != 0)
  {
    stop = 0;
    for (i = 0; i < set_size - 1; i++)
    {
      if (array[i] > array[i + 1])
      {
        temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
        stop = 2;
      }
    }
  } 
}
