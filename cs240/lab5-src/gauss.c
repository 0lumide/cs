#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MAXCOLS 101

void printMatrix(int rows, int columns, double ** mat);//matrix[][MAXCOLS]);
char * getInput();
int isNum(char c);
int charToNum(char c);
int strToNum(char * str);
int strIsNum(char * str);
void printMissingEl(int i, int j);
void sizeError();
void swapRows(int i, int j, double ** mat);
void divideRow(int i, int colNum, double** mat);
void makeZero(int x, int y, int colNum, double ** mat);
/* Add helper functions and global variables here */
void printMissingEl(int i, int j){
	printf("Element a[%d][%d] is missing\n", i, j);
	exit(0);
}

void sizeError(){
	printf("Expected N (number of equations)\n");
	exit(0);
}
int isNum(char c){
	if(((c != '.')&&(c != '-'))&&((c > 57) || (c < 48)))
		return 0;
	return 1;
}
int charToNum(char c){
	return c - 48;
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
//This method offers no error checking, so use strIsNum to check if string is a valid number before 
//using this function
int strToNum(char * str){
	int num = 0;
	int i = 0;
	while((str[i] != '\0')&&(str[i] != '\n')){
		num += charToNum(str[i]) * pow(10, i);
		i++;
	}
	return num;
}

//double** getInput(int * col, int * row){
char * getInput(){
	char * temp = malloc(sizeof(char));
	temp[0] = '\0';
	int started = 0;
	int stop = 0;
	char c;

	//Begin new method
	while((!stop)){
		c = getchar();
		if((c != EOF)&&(c != ' ') && (c != '\n')){
			started = 1;
			int len = strlen(temp);
			temp = realloc(temp, sizeof(char) * (len + 1));
			temp[len] = c;
			temp[len + 1] = '\0';
			//printf("%s\n",temp);
			started = 1;
		}
		else if(started || (c == EOF))
			break;
	}
	return temp;
}

void swapRows(int row1, int row2, double ** mat){
	double * temp = mat[row1];
	mat[row1] = mat[row2];

	mat[row2] = temp;
}

void divideRow(int i, int colNum, double** mat){
	int j;
	double temp = mat[i][i];
	for(j = i; j < colNum; j++)
		mat[i][j] = mat[i][j] / temp;
}

void makeZero(int x, int y, int colNum, double ** mat){
	int i;
	double temp = mat[x][y];
	if(x != y){
		for(i = y; i < colNum; i++){
			mat[x][i] = mat[x][i] - (mat[y][i] * temp);
		}
	}
}
int main()
{
	/* Add your implementation here */
	int i;
	int j;
	int size;
	int colNum;
	int rowNum;
	char * pointless;
	double ** mat;
	char * input = getInput();
	if(strIsNum(input)){
		size = strtod(input, &pointless);//strToNum(input);
	}
	else
		sizeError();
	rowNum = size;
	colNum = size + 1;
	//Create matrix array
	mat = malloc(sizeof(double *) * rowNum);

	for(i = 0; i < size; i++){
		mat[i] = malloc(sizeof(double) * colNum);
		for(j = 0; j < colNum; j++){
			input = getInput();
			if((strlen(input) == 0)||!strIsNum(input))
				printMissingEl(i, j );
			else
				mat[i][j] = strtod(input, &pointless);
		}
	}
	printf("initial matrix\n");
	printMatrix(rowNum, colNum, mat);
	for(i = 0; i < rowNum; i++){
		int maxPos = i;
		//find the maximum value of abs(r[j][i]) where j >= i
		for(j = i + 1; j < rowNum; j++){
			if(fabs(mat[j][i]) > fabs(mat[maxPos][i])){
				maxPos = j;
			}
		}
		// this makes the pivot nonzero if possible
		int isSingular = 1;
		for(j = 0; j < rowNum; j++){
			if(mat[i][j] != 0){
				isSingular = 0;
				break;
			}
		}
		if(isSingular){
			printf("Error: Matrix is singular\n");
			exit(0);
		}			
		printf("swapped %d and %d\n", i, maxPos);
		swapRows(maxPos, i, mat);
		printMatrix(rowNum, colNum, mat);
		printf("row %d /= %f\n", i, mat[i][i]);
		divideRow(i, colNum, mat);
		printMatrix(rowNum, colNum, mat);
		for(j = 0; j < rowNum; j++){
			if(i != j){
				printf("row %d -= %f row %d\n", j, mat[j][i], i);
				makeZero(j, i, colNum, mat);
				printMatrix(rowNum, colNum, mat);
			}
		}
	}
	for(i = 0; i < rowNum; i++){
		printf("%f ",mat[i][size]);
	}
	printf("\n");
	return 0;
}

void printMatrix(int rows, int columns, double ** mat)//matrix[][MAXCOLS])
{
	/* Add your implementation here */
	int i;
	int j;
	printf("MATRIX:\n");
	for(i = 0; i < rows; i++){
		for(j = 0; j < columns; j++){
			printf("%f ",mat[i][j]);
		}
		printf("\n");
	}
	printf("\n");

}
