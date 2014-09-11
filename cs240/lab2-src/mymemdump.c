#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void mymemdump(FILE * fd, char * p , int len) {
  // Add your code here.
  // You may see p as an array.
  // p[0] will return the element 0 
  // p[1] will return the element 1 and so on

  
  //fprintf(fd, "0x%016lX: ", (unsigned long) p);
  int i = 0;
  int j = 0;
  int c;
  int lineCount = len / 16;
  if((len % 16) != 0){
    lineCount++;
  }

  for( int k = 0; k < lineCount; k++){
    fprintf(fd, "0x%016lX: ", (unsigned long) p);
    while((!i) || ((i % 16) != 0)){
      c = p[i++]&0xFF;
      // Print first byte as hexadecimal
      if((i+ 16 * k) > len){
        fprintf(fd, "   ");
      }
      else {
        fprintf(fd, "%02X ", c);
      }
    }
    fprintf(fd, " ");
    while((!j) || ((j % 16) != 0)){
      c = p[j++]&0xFF;
      // Print first byte as character
      if((j + 16 * k) > len){
        //fprintf(fd, "."); 
      }
      else{
        fprintf(fd, "%c", (c>=32)?c:'.');
      }
    }
    fprintf(fd, "\n");
    //int c = p[i++]&0xFF;
    // Print first byte as hexadecimal
    //fprintf(fd, "%02X ", c);


    // Print first byte as character
    //fprintf(fd, "%c", (c>=32)?c:'.');
    //fprintf(fd,"\n");
    i = 0;
    j = 0;
    p+= 16;
  }
}
