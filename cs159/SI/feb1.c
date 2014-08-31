#include<stdio.h>
#include<math.h>

float getInput(float*, float*, float*);

float calcVolume(float, float, float);

int main()
{
  float height;
  float depth;
  float length;
  float vol;

  getInput(&height, &depth, &length);

  vol = calcVolume(height, depth, length);
  printf("The Volume of your Solid square prism is: %5.3f", vol);
  return(0);
}

float getInput(float *ah, float *ad, float*al)
{
  printf("Enter the Height: ");
  scanf("%f", ah);
  printf("Enter the Depth: ");
  scanf("%f", ad);
  printf("Enter the Length: ");
  scanf("%f", al);
  return(0);
}

float calcVolume(float ah, float ad, float al)
{
  return(ah * ad * al);
}
