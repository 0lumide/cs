
/***************************************************************************
*
*  Programmer and Purdue Email Address:
*  1. oawofeso@purdue.edu
*
*  Homework #:03
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
*  Program Description:This program tells the correct time based off a reflection of a clock.
*
***************************************************************************/

#include<stdio.h>

//GLOBAL DECLARATION

void getInput(int*, int*);
void calcRealTime(int*, int*);
void printOutput(int, int, int, int);

int main()
{
  
  //LOCAL DECLARATION

  int hour_seen; //This is the observed hour value in the reflection
  int min_seen; //This is the observed minute value in the reflection
  int real_hour; //This is the actual hour value
  int real_minute; //This is the actual minute value

  //EXECUTABLE STATEMENTS

  getInput(&hour_seen, &min_seen);
  real_hour = hour_seen;
  real_minute = min_seen;
  calcRealTime(&real_hour, &real_minute);
  printOutput(hour_seen, min_seen, real_hour, real_minute);
  return(0);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInput
*
*     Programmer's Name:
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int* hour //This is the value of the hour seen in the reflection
*       2.int* minute //This is the value of the minute seen in the reflection of the clock
*
*     Function Description: This function gets the time seen in the reflection from the user.
*
*
***************************************************************************/


void getInput(int* hour, int* minute)
{
  //EXECUTABLE STATEMENTS

  printf("\nEnter the observed hour value: ");
  scanf("%d", hour);
  printf("Enter the observed minute value: ");
  scanf("%d", minute);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcRealTime
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int* hour //This is the value of the hour seen in the reflection
*       2.int* min //This is the value of the minute seen in the reflection of the clock
*
*     Function Description: This function calculates the actual time from the time seen in the reflection.
*
***************************************************************************/


void calcRealTime(int* hour, int* min)
{
  //EXECUTABLE STATEMENTS
  
  if ((*hour == 0 ||(*hour == 12)) && (*min == 0))
  {
    
  }
  else if ((*hour < 12) && (*min != 0))
  {
    *hour = 11 - *hour;
    *min = 60 - *min;
  }
  else if ((*hour < 12) && (*min == 0))
  {
    *hour = 12 - *hour;
    *min = 0;
  }
  else if ((*hour >= 12) && (*min != 0))
  {
    *hour = 35 - *hour;
    *min = 60 - *min;
  }
  else
  {
    *hour = 36 - *hour;
    *min = 0;
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
*     Function Return Type:
*
*     Parameters (list data type, name, and comment one per line):
*       1.int hour_seen //value of hour seen in reflection
*       2.int min_seen //value of the minute seen in reflection
*       3.int real_hour //actual hour value
*       4.int real_min //actual minute value
*    
*       Function Description: This function displays the time seen in the reflection and the calculated actual time
*
***************************************************************************/


void printOutput(int hour_seen, int min_seen, int real_hour, int real_min)
{
  //EXECUTABLE STATEMENTS
  printf("\nReflected Time: %02d:%02d",hour_seen, min_seen);
  printf("\nActual Time: %02d:%02d\n\n", real_hour, real_min);
}
