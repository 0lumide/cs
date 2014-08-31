
/***************************************************************************
 *
 *  Programmers and Purdue Email Addresses:
 *	 1. Olumide Awofeso oawofeso@purdue.edu
 *	 2. Jordan Roark roark2@purdue.edu
 *	 3. Izzat Ghuneim ighuneim@purdue.edu (delete line if no third partner)
 *
 *  Lab #: 10
 *
 *  Academic Integrity Statement:
 *
 *       We have not used source code obtained from
 *       any other unauthorized source, either modified
 *       or unmodified.  Neither have we provided access 
 *       to our code to another. The project we are submitting
 *       is our own original work.
 *
 *  Lab Division and Section: 0101
 *
 *  Program Description: Counts odd digits and prints Valid and invalid inputs
 *
 ***************************************************************************/


#include <stdio.h>
#define AMOUNT 10
#define AMOUNTS 5

int Validate(int);
int getInput(int);
void printValid(int, int, int *);
void printInvalid(int, int, int *);

int main()
{
  //LOCAL DECLARATION
  int y;
  int numbers[AMOUNT] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  int valid[AMOUNT] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  int stop = 0;
  int stop2 = 0;

  //EXECUTABLE STATEMENTS
  for (y = 0; y < AMOUNT; y++)
  {  
    numbers[y] = getInput(y);
    valid[y] = Validate(numbers[y]);
  }


  for (y = 0; y < AMOUNT; y++)
  {
    printValid(numbers[y], valid[y], &stop);
  }


  for (y = 9; y >= 0; y--)
  {
    printInvalid(numbers[y], valid[y], &stop2);
  }

  printf("\n\n");
  return (0);

}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:
 *
 *     Programmer's Name:
 *
 *     Function Return Type:
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.
 *       2.
 *       3.
 *
 *     Function Description:
 *
 ***************************************************************************/

int getInput(int num)
{
  int Input;
  printf("Enter data #%d: ",num + 1);
  scanf("%d",&Input);
  return(Input);
}

/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:
 *
 *     Programmer's Name:
 *
 *     Function Return Type:
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.
 *       2.
 *       3.
 *
 *     Function Description:
 *
 ***************************************************************************/

int Validate(int x)
{
  int count[AMOUNTS] = {0,0,0,0};
  int y = x;
  int counter = 0;
  int validity = 0;
  int i;

  while (y > 0)
  {
    x = y % 10;
    y = y / 10;
    switch(x)
    {
      case 1: count[0]++;
              break;
      case 3: count[1]++;
              break;
      case 5: count[2]++;
              break;
      case 7: count[3]++;
              break;
      case 9: count[4]++;
              break;
    }
  }

  for (i = 0; i < 5; i++)
  {
    if (count[i] == 0)
    {
      counter++;
    }
  }


  if (counter == 4)
  {
    for (i = 0; i < 5; i++)
    {
      if (count[i] >= 2)
      {
        validity = 1;
      }
    }
  }

  return(validity);
}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:
 *
 *     Programmer's Name:
 *
 *     Function Return Type:
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.
 *       2.
 *       3.
 *
 *     Function Description:
 *
 ***************************************************************************/

void printValid(int first, int second, int *stop)
{
  if (second == 1)
  {
    if (*stop == 0)
    {
      printf("\nValid Values: ");
      *stop = 1;
    }
    printf(" %d", first);
  }
}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function:
 *
 *     Programmer's Name:
 *
 *     Function Return Type:
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1.
 *       2.
 *       3.
 *
 *     Function Description:
 *
 ***************************************************************************/

void printInvalid(int first, int second, int *stop)
{
  if (second == 0)
  {
    if (*stop == 0)
    {
      printf("\nValid Values: ");
      *stop = 1;
    }
    printf(" %d", first);
  }
}

