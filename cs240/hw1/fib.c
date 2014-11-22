#include <stdio.h>
#include <stdlib.h>
#include <math.h>
void printWelcome();
int getInput();
int calcFib(int n);
void printFib(int k, int fib);
int shouldCont();

int shouldCont(){
	//This function reads only the first char, all other characters are ignored
	printf("Do you want to continue y/n? ");
	char inp = getchar();
	while(getchar() != '\n');
	if(inp == 'n')
		return 0;
	else if(inp == 'y')
		return 1;
	else
		return shouldCont();
}

void printFib(int k, int fib){
	printf("The %dth Fibonacci number is %d\n", k, fib);
}
int calcFib(int n){
	switch(n){
		case 0: return 0;
		case 1: return 1;
		default: return(calcFib(n - 1) + calcFib(n -2));
	}
}
int getInput(){
	//This function does not do proper valid input checking
	//It doesn't prompt the user to enter a correct value if entered value wasn't int an integer
	int num;
	printf("Input k? ");
	if(scanf("%d", &num) < 0)
		exit(0);
	while(getchar() != '\n');
	if(num < 0)
		exit(0);
	return num;
}
void printWelcome(){
	printf("Program Fibonacci\n");
}
int main(){
	int num;
	int fib;
	printWelcome();
	do{
		num = getInput();
		fib = calcFib(num);
		printFib(num, fib);
	}while(shouldCont());
	printf("Bye!\n");
}

