#include <stdio.h>
int welcome(int, int);

int main()
{

  int z = 11;
  
  z =  welcome(y);
  
  printf("z =: %d\n", z);

  return(0);
}

int welcome(int x, int y)
{

  y = 5 +x;
  printf("%d\n", x);
  return(y);

}
