#include <stdio.h>
int main()
{
  int a = 3;
  int y;

  y = a-- + a;
  printf("\n\n%d\n\n", y);
  char s = 0;
  printf("\nMonth%4cOld%5cMonthly Interest Principal%4cNew\n",s,s,s);
  printf("%7cBalance  Payment%4cPaid%6cPaid%5cBalance\n",s,s,s,s);
  printf("\nMonth%4cOld%5cMonthly Interest Principal%4cNew\n%7cBalance  Payment%4cPaid%6cPaid%5cBalance\n",s,s,s,s,s,s,s);

  return(0);
}
