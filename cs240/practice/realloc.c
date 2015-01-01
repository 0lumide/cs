#include <stdlib.h>
#include <string.h>
#include <stdio.h>
void append(char * str, char c){
	str = (char *)realloc(str, 3);
	//str = (char *)malloc(3);
	//str[0] = 'b';
	str[1] = c;
	str[2] = 0;
}

int main(){
	char * str = (char *) malloc(2);
	char * str1 = (char *) malloc(200);
	str1[0] = 'r';
	str1[199] = 0;
	str[0] = 'a';
	str[1] = 0;
	printf("before: %s, pointer:%p\n", str, str);
	append(str, 'b');
	printf("after: %s, pointer:%p\n", str, str);
	int i = 2;
	printf("%d\n",i);
	i *= 3;
	printf("%d\n",i);
}
