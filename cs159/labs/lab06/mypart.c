#include<stdio.h>
#include<math.h>

void harmonic();
int main()
{
  harmonic();
  return(0);
}

void harmonic()
{
  //LOCAL DECLARATION
  int count_val;
  int exit_val;
  float data_x;
  float add;
  float harm_mean;
  
  //EXECUTABLE STATEMENT
  count_val = 1;
  exit_val = -1;
  add = 0;
  
  do
  {
    printf("Enter data value #%d or %d to exit: ", count_val, exit_val);
    scanf("%f", &data_x);
    if (data_x != exit_val)
    {
      add = ((1 / data_x) + add);
      count_val++;
    }
  }while (data_x != exit_val);
  harm_mean = (--count_val) / add;
  printf("\nHarmonic Mean Calculation");
  printf("\nSet Size: %d", count_val);
  printf("\nMean: %.4f\n\n", harm_mean);
}
