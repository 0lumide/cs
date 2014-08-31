
/***************************************************************************
*
*  Programmer and Purdue Email Address:
*  1. oawofeso@purdue.edu
*
*  Homework #:06
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
*  Program Description: This program find and prints a group of numbers together based on a predetermined criteria
*
***************************************************************************/


#include<stdio.h>
#include<math.h>

#define AMOUNT 10
#define AMOUNTS 100

int getInput(int);
void countnum(int, int[], int);
void printfriend(int[], int[]);

int main()
{
  //LOCAL DECLARATION
  int i; // This controls the first for loop
  int data[AMOUNT] = {0,0,0}; //This array hold the data entered by the user
  int datatype[AMOUNTS] = {0,0,0}; //This array holds the number of each digit in each data entered by the user


  // EXECUTABLE STATEMENT
  for (i = 0; i < AMOUNT; i++)
  {
    data[i] = getInput(i);
  }

  for (i = 0; i < AMOUNT; i++)
  {
    countnum(i, datatype , data[i]);
  }

  printfriend(data, datatype);

  return(0);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInput
*
*     Programmer's Name: Olumide Awofeso
*
*     Function Return Type:int
*
*     Parameters (list data type, name, and comment one per line):
*       1. int num //This is the value that is printed to the screen during the input process
*
*     Function Description: This function gets input that is used in this program
*
***************************************************************************/

int getInput(int num)
{
  //LOCAL DECLARATION
  int val;// This holds the data enterd by the user temporarily

  //EXECUTABLE STATEMENT
  printf("Enter data #%d: ", ++num);
  scanf("%d", &val);

  return(val);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:countnum
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type: void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int i //This the the reference number for the array which is worked on
*       2.int datatype[] //This is the arra containing the data entered by the user
*       3.int data// This is the value stored int the array to be worked on
*
*     Function Description:This function counts how many of each digits a number has
*
***************************************************************************/

void countnum(int i, int datatype[], int data)
{
  //LOCAL DECLARATION
  int y = data; //This is used to control the while loop
 
  //EXECUTABLE STATEMENTS
  while (y > 0)
  {
    data = y % 10;
    y = y / 10;
    switch(data)
    {
      case 0: datatype[i*10]++;
              break;
      case 1: datatype[i * 10 + 1]++;
              break;
      case 2: datatype[i * 10 + 2]++;
              break;
      case 3: datatype[i * 10 + 3]++;
              break;
      case 4: datatype[i * 10 + 4]++;
              break;
      case 5: datatype[i * 10 + 5]++;
              break;
      case 6: datatype[i * 10 + 6]++;
              break;
      case 7: datatype[i * 10 + 7]++;
              break;
      case 8: datatype[i * 10 + 8]++;
              break;
      case 9: datatype[i * 10 + 9]++;
              break;
    }
  }
}




/***************************************************************************
*
*     Function Information
*
*     Name of Function:printfriend
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int data[] //This is the array containing the data entered by the user
*       2.int datatype[] //This is the array containing the amount of each digit each value in the data array has
*
*     Function Description: This function finds and prints friends
*
***************************************************************************/

void printfriend(int data[], int datatype[])
{
  //LOCAL DECLARATION

  int i;   //This variable is used in the first for loop
  int j;   //This variable is used in the third for loop
  int set;   //this variable determined whether the functon prints a pair of numbers as friends or not
  int k;    //This variable is used in the second for loop
  int l = 0;   // This variable determines whether this function prints a new line or not
  int print_tell = 1;   // This variable determines whether this function prints "Friend: " or not


  //EXECUTABLE STATEMENTS
  for (i = 0; i < AMOUNT; i++)
  {
    for (k = i + 1; k < AMOUNT; k++)
    {
      set = 3;
      for (j = 0; j < AMOUNT; j++)
      {
        if ((datatype[10 * i + j] != 0) && (set !=2))
        {
          if (datatype[10 * k + j] != 0)
          {
            set = 1;
          }
          else
          {
            set = 2;
          }
        }
      }
      if (set == 1)
      {
        if ((l % 5) == 0)
        {
          printf("\n");
        }
        if (print_tell == 1)
        {
          printf("Friends: ");
          print_tell = 0;
        }
        printf("[%d, %d] ", data[i], data[k]);
        l++;
      }
    }
  }
  printf("\n\n");
}
