#include <string.h>
#include <stdio.h>
#include <math.h>


int main()
{
  int SIZE = 100;
  char str1[SIZE];
  int lcv = 0;

  printf("Enter a String: ");
  gets(str1);

  while (str1[lcv] != '\0')
  {
    if (str1[lcv] >= 'A')
    {
      if (str1[lcv] < 'N')
      {
        str1[lcv] = str1[lcv] + 13;
      }
      else if (str1[lcv] <= 'Z')
      {
        str1[lcv] = str1[lcv] - 13;

      }
      else if ((str1[lcv] >= 'a') && (str1[lcv] < 'n'))
      {
        str1[lcv] = str1[lcv] + 13;

      }
      else
      {
        str1[lcv] = str1[lcv] - 13;
      }
    }
    lcv++;
  }
  printf("The String you entered was %s.\n\n", str1);
  return(0);
}

