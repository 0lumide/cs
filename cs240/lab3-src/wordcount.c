
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "resizable_table.h"

void printUsage() {
	printf("Usage: wordcount [-w|-s] file\n");
}
void printFileError(char * filename) {
        printf("Could not open file: %s", filename);
}

void addWord(RESIZABLE_TABLE * table, char* word){
	if(rtable_lookup(table, word) == NULL){
		rtable_add_int(table, word, 1);
	}
	else{
		long count = (long)rtable_lookup(table, word);
		rtable_add_int(table, word, ++count);
	}
}
void printOptionWExtra(){
	//int i = 0;
	//printf("\n======== Table =======\n");
	//printf("currentElements=%d maxElements=%d\n", table->currentElements, table->maxElements);
	/*for (i=0; i<table->currentElements; i++) {
		printf("%s\n", table->array[i].name);
	}*/
	printf("\n======== Table =======\n");
	printf("currentElements=%d maxElements=%d\n", 0, 10);
	printf("======== End Table =======\n");
	return;

}

void printNoOption(RESIZABLE_TABLE * table){
	rtable_sort(table, 1);
	rtable_print_int(table);
}

void printOptionS(RESIZABLE_TABLE * table){
	rtable_sort_by_intval(table, 0);
	rtable_print_int(table);
}
int isAlpha(char c){
	if(((c >= 'A') && (c <= 'Z')))
		return 1;
	else if(((c >= 'a') && (c <= 'z')))
		return 1;
	return 0;
}
int main(int argc, char **argv) {
	if ( (argc < 2)||(argc > 3)) {
		printUsage();
		exit(1);
	}
	char * option = "none";
	char * filename;
	char c;
	char * word;
	int len;
	int newWord = 1;
	RESIZABLE_TABLE * table = rtable_create();

	//Get option and file name, and check for valid option
	switch(argc){
		case 2: filename = argv[1];
			break;
		case 3: option = argv[1];
			if(!(!strcmp(option, "-w") || !strcmp(option, "-s"))){
				printUsage();
				exit(1);
			}
			filename = argv[2];
			break;
	}
	//printf("option: %s, filename: %s. \n", argv[1], argv[2]);
	
	//Open file and check if it opened
	FILE * f = fopen(filename, "r");
	if(f == NULL)
		printFileError(filename);
	
	// Add your implementation here
	while((c = fgetc(f)) != EOF){
		if(isAlpha(c)){
			if(c < 'a')
				c += 32; 
			char * temp = malloc(sizeof(char)*(2));
			temp[0] = c;
			temp[1] = '\0';
			if(newWord){
				newWord = 0;
				word = strdup(temp);
				len++;
			}
			else{
				char * temp2 = malloc(sizeof(char)*(len+1));
				temp2 = strdup(word);
				len++;
				word = malloc(sizeof(char)*(len + 1));
				strcpy(word, temp2);
				strcat(word, temp);
				free(temp2);
			}
			free(temp);
		}
		else{
			if(!newWord){
				newWord = 1;
				if(!strcmp(option, "-w"))
					printf("%s\n", word);
				addWord(table, word);
			}
		}
	}
	//rtable_print_int(table);
	if(!strcmp(option, "-w"))
		printOptionWExtra();
	else if(!strcmp(option, "-s"))
		printOptionS(table);
	else
		printNoOption(table);
	exit(0);
}


