#include <stdio.h>

int main(int argc, char **argv) {
  int high;
  int low;
  int mid;
  char c;
  int continueLoop = 1;
  printf("Welcome to the High Low game...\n");

  // Write your implementation here...
  while(continueLoop){
  high = 100;
  low = 0;
    printf("Think of a number between 1 and 100 and press press <enter>\n");
    while(high >= low){
      mid = (high + low)/2;
      printf("Is it higher than %i? (y/n)\n", mid);
      //scanf("%c\n\n",&c);
      //c = getchar();
      //getchar();
      while((c = getchar() != EOF)) {
        
      }
      printf("This is the input: %c\n", c);
      if( c == 'y'){
        low = mid + 1;
      }
      else if(c == 'n'){
        high = mid - 1;  
      }
      else {
        printf("Type y or n\n");
      }
    }
    printf("\n>>>>>> The number is %i\n", low);
    printf("Do you want to continue playing (y/n)?");
    c = getchar();
    getchar();
    if(c == 'y'){

    }
    else if(c == 'n'){
      continueLoop = 0;
      printf("Thanks for playing!!!\n");
    }
  }
}

