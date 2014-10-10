#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MAXROWS 100
#define MAXCOLS 101

void printMatrix(int rows, int columns, double matrix[][MAXCOLS]);

/* Add helper functions and global variables here */
void printMissingEl(int i, int j){
	printf("Element a[%d][%d] is missing\n", i, j);
	exit(0);
}
int isNum(char c){
	if((c > 57) || (c < 48))
		return 0;
	return 1;
}
int charToNum(char c){
	return c - 30;
}

int strIsNum(char * str){
	int i = 0;
	while((str[i] != '\0')&&(str[i] != '\n')){
		if(!isNum(str[i++]))
			return 0;
	}
	if(i == 0)
		return 0;
	return 1;
}
double** getInput(int * col, int * row){
	char * temp = malloc(sizeof(char)*2);
	size_t limit = 1;
	int size = -1;
	int i;
	int j;
	char c;
	double notNewNum;
	char ** input;
	double num;
	int totCount = 1;

	//Begin new method
	while((count < totCount)){
		if(size == -1){
			
		}
	}
	//End new method

	/*getline(&temp, &limit, stdin);
	while(strcmp(temp, "\n") == 0){
		getline(&temp, &limit, stdin);
	}
	if(!strIsNum(temp)){
		printf("Expected N (number of equations)\n");
		exit(0);
	}
	size = atoi(temp);
	input = malloc(sizeof(char) * size);
	//printf("size: %d\n", size);
	double** ret = malloc(sizeof(double)*(size + 1));
	for(i = 0; i < size; i++){
		//getline(&temp, &limit, stdin);
		//input[i] = strdup(temp);
		j = 0;
		ret[i] = malloc(sizeof(double) * size);
		notNewNum = 0;
		num = 0;
		while((c = getchar())!= '\n'){
			if((!isNum(c))&&(c != ' ')){
				printMissingEl(i, j);
			}
			else if(c == ' '){
				if(notNewNum){
					j++;
					ret[i][j] = num;
					num = 0;
					notNewNum = 0;
				}
			}
			else{
				num += charToNum(c) * pow(10, notNewNum);
				notNewNum++;
			}
			j++;	
		}
	}
	return ret;
	*/
}

int main()
{
	/* Add your implementation here */
        int colNum;
	int rowNum;
	double** input = getInput(&colNum, &rowNum);

	return 0;
}

void printMatrix(int rows, int columns, double matrix[][MAXCOLS])
{
	/* Add your implementation here */
}
