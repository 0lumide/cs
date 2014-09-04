
/***************************************************************************
*
*  Programmer and Purdue Email Address:
*  1. oawofeso@purdue.edu
*
*  Homework #:01
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
*  Program Description:This program converts angles from Degrees, minutes, and seconds to Radians.
*
***************************************************************************/

#include<stdio.h>
#include<math.h>

int main(void)
{
  //LOCAL DECLERATIONS

  int num_deg; //NUMBER OF DEGREES
  int num_min; //NUMBER OF MINUTES
  int num_sec; //NUMBER OF SECONDS
  char char_deg; //DEGREE SYMBOL
  char char_min; //MINUTE SYMBOL
  char char_sec; //SECOND SYMBOL
  float num_rad; //ANGLE IN RADIANS
  float sec_min; //SECONDS IN MINUTES
  float min_deg; //MINUTES IN DEGREES
  int div_cons2; //A CONSTANT USED IN THE CONVERSION FROM DEGREE TO RADIANS
  float div_cons1; //A CONSTANT USED TO CONVERT ECONDS TO MINUTE ANS MINUTE TO DEGREE

  //EXECUATBLE STATEMENTS

  printf("\nEnter number of degrees: ");
  scanf("%d", &num_deg);
  
  printf("Enter number of minutes: ");
  scanf("%d",&num_min);
  
  printf("Enter number of seconds: ");
  scanf("%d",&num_sec);
  
  div_cons2 = 180;
  div_cons1 = 60;
  char_deg = 176;
  char_min = 39;
  char_sec = 34;
  
  printf("\nData Entered: %d%c %d%c %d%c",num_deg,char_deg,num_min,char_min,num_sec,char_sec);

  sec_min = (num_sec / div_cons1);
  min_deg = ((num_min + sec_min)/div_cons1);
  num_rad = (((min_deg + num_deg)*M_PI)/div_cons2);

  printf("\nRadians Conversion: %5.4f\n\n",num_rad);
   
  return 0;
}


