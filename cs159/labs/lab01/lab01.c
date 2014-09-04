
/***************************************************************************
*
*  Programmers and Purdue Email Addresses:
*	 1. ncosselm@purdue.edu
*	 2. saudi@purdue.edu
*	 3. oawofeso@purdue.edu (delete line if no third partner)
*
*  Lab #:01
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
*  Program Description:
*
***************************************************************************/


#include<stdio.h>
#include<math.h>

int main(void)
{
  //LOCAL DECLERATIONS

  float x_coor_1; //USER INPUT FOR THE X COORDINATE OF THE FIRST CIRCLE
  float x_coor_2; //USER INPUT FOR THE X COORDINATE OF THE SECOND CIRCLE
  float radius_1; //USER INPUT FOR THE RADIUS OF THE FIRST CIRCLE
  float radius_2; //USER INPUT FOR THE RADIUS OF THE SECOND CIRCLE
  float x_var; //DISTANCE BETWEEN CENTER OF FIRST CIRCLE AND X COORDINATE OF POINT OF INTERSECTION
  float y_coor; //Y COORDINATE OF POINT OF INTERSECTION OF THE TWO CIRCLES
  float ang_1; //ANGLE MADE AT CENTRE OF FIRST CIRCLE BY LINE FROM CENTERE TO POINT OF INTERSECTION IN RADIANS
  float ang_2; //ANGLE MADE AT CENTER OF SECOND CIRCLE BY LINE FROM CENTRE TO POINT OF INTERSECTION IN RADIANS
  float area_final; //AREA OF THE OVERLAPPING AREA OF THE TWO CIRCLES 

  //EXECUTABLE STATEMENTS
  printf("\n\nEnter the x coordinate for the first cicle : ");
  scanf("%f", &x_coor_1);
  
  printf("Enter the radius for the first circle : ");
  scanf("%f", &radius_1);
  
  printf("Enter the x coordinate for the second circle : ");
  scanf("%f", &x_coor_2);
  
  printf("Enter the radius for the second circle : ");
  scanf("%f", &radius_2);
  
  x_coor_2 = x_coor_2 - x_coor_1;
     
  x_var = ((radius_1 * radius_1) - (radius_2 * radius_2) + (x_coor_2 * x_coor_2)) / ((2 * x_coor_2));
  
  y_coor = sqrt((radius_1 * radius_1) - (x_var * x_var));
  
  ang_1 = asin((y_coor) / (radius_1));
  
  ang_2 = asin(y_coor / radius_2);

area_final =0.5 * ((radius_1 * radius_1) * (ang_1 - (cos(ang_1) * sin(ang_1))) + (radius_2 * radius_2) * (ang_2 - (cos(ang_2) * sin(ang_2))));

printf("\nArea of the intersection %4.2f\n\n\n", area_final);
        
  return 0;

}
