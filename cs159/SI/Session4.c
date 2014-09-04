#include <stdio.h>

void getInput(int*);
int getInput_v2(void);

int main()
{
  int x;
  getInput(&x);
  //x = getInput_v2();  
  x = (x / 10)-(x-10)/10;
  printf("\n\nx = %d\n\n",x);
  return(0);
}

void getInput(int *entry)
{
  printf("Enter the value: ");
  scanf("%d", entry);
}

int getInput_v2()
{
  int y;
  printf("Enter the value: ");
  scanf("%d", 
