
/***************************************************************************
*
*  Programmers and Purdue Email Addresses:
*	 1. saudipurdue.edu
*	 2. ncossem@purdue.edu
*	 3. @purdue.edu (delete line if no third partner)
*
*  Lab #:02
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
#include <stdio.h>
#include <math.h>

int main()
{
  float low_rad;
  float up_rad;
  float slant_h;
  float height;
  float vol_water;

  printf("\n Enter the Lower Radius :");
  scanf("%f", &low_rad);
  printf("\n Enter the Upper Radius :");
  scanf("%f", &up_rad);
  printf("\n Enter the Slant height :");
  scanf("%f",&slant_h);

  height = sqrt((slant_h*slant_h)-((up_rad-low_rad)*(up_rad-low_rad)));
  printf("\n Vertical height of water : %5.4f ", height);
  vol_water = (M_PI / 3) * (((low_rad*low_rad) * (height + ((height * up_rad)/(low_rad - up_rad)))) - ((up_rad * up_rad) * ((height * up_rad)/(low_rad - up_rad))));
  printf("\n Volume of Water in the Tank : %7.4f \n", vol_water);
  
 return (0);
}

