#include <stdio.h>

int main() {
    int i = 1;
    int *d = &i;
    printf("%d \n\n\n\n\n\n\n",i);
    
    printf("d = %x\n", d);
    printf("&d = %x\n", &d);

    printf("&i = %x\n", &i);
    printf("main = %x\n", main);
    printf("&main = %x\n", &main);
    return 0;
}
