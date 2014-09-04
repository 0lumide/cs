#define SUBSTR 20
#define STRING 50

#include <stdio.h>
#include <string.h>
#include <math.h>

void getStr(char[], char[]);
int ctStrings(char[], char[]);

int main()
{
  char sub[SUBSTR + 1];
  char string[STRING + 1];
  int subN = 0;

  getStr(string, sub);
  subN = ctStrings(string, sub);

  printf("The String %s appears %d times in the string %s.\n\n", sub, subN, string);

  return(0);
}

int ctStrings(char str[], char sub[])
{
  int count = 0;
  char *pt = NULL;

  do 
  {
    if(pt != NULL)
    {
      count ++;
      pt = strstr(pt + 1, sub);
    }
    else
    {
      pt = strstr(str, sub);
    }
  }while ( pt != NULL);

  return(count);
}

void getStr(char x[], char y[])
{
  printf("Enter The String: ");
  gets(x);

  printf("Enter The Substring: ");
  gets(y);
}
