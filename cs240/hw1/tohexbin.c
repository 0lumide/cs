#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

char * toBaseN(int num, int base);
void errorMessage();
int isDecimal(char * suspect);
char intToChar(int num);

int main(int argc, char * argv[]){
	if(argc < 2)
		errorMessage();
	else if(!isDecimal(argv[1]))
		errorMessage();
	int num = atoi(argv[1]);
	char * bin = toBaseN(num, 2);
	char * hex = toBaseN(num, 16);
	printf("Binary: 0b");
	int i;
	for(i = strlen(bin) -1; i >= 0; i--)
		printf("%c",bin[i]);
	printf("\nHexadecimal: 0x");
	for(i = strlen(hex) - 1; i>=0; i--)
		printf("%c",hex[i]);
	printf("\n");
}

char * toBaseN(int num, int base){
	int size = 10;
	int count = 0;
	char * arr = (char *)malloc(sizeof(char)*size);
	while(num != 0){
		if(count == size-1){
			size *=2;
			arr = realloc(arr, sizeof(char)*size);
		}
		arr[count] = intToChar(num % base);
		arr[++count] = 0;
		num -= num % base;
		num = num/base;
	}
	return arr;
}

void errorMessage(){
	printf("Error your input is either missing or not a Decimal\n");
	exit(1);
}

int isDecimal(char * suspect){
	while(*suspect != 0){
		if(!((*suspect >= '0') && (*suspect <= '9')))
			return 0;
		else suspect++;
	}
	return 1;
}

char intToChar(int num){
	if((num >= 0) && (num <= 9))
		return (char)(num + '0');
	return (char)(num - 10 + 'A');
}
