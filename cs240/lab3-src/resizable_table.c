
#include <stdlib.h>
#include <assert.h>
#include <stdio.h>
#include <string.h>
#define INITIAL_SIZE_RESIZEBLE_TABLE = 10;
#include "resizable_table.h"

//
// It returns a new RESIZABLE_TABLE. It allocates it dynamically,
// and initializaes the values. The initial maximum size of the array is 10.
//
RESIZABLE_TABLE * rtable_create() {

	// Allocate a RESIZABLE_TABLE
	RESIZABLE_TABLE * table = (RESIZABLE_TABLE *) malloc(sizeof(RESIZABLE_TABLE));
	if (table == NULL) {
		return NULL;
	}
	
	// Initialze the members of RESIZABLE_TABLE
	table->maxElements = INITIAL_SIZE_RESIZABLE_TABLE;
	table->currentElements = 0;
	table->array = (struct RESIZABLE_TABLE_ENTRY *)
		malloc(table->maxElements*sizeof(RESIZABLE_TABLE_ENTRY));
	if (table->array==NULL) {
		return NULL;
	}
	
	return table;
}

//
// It prints the elements in the array assuming the value is a string in the form:
//
//======== Table =======
//currentElements=2 maxElements=10
//0: "George"       "23 Oak St, West Lafayette, 47906"
//1: "Peter"        "27 Oak St, West Lafayette, 47906"
//======== End Table =======
//
void rtable_print_str(RESIZABLE_TABLE * table)
{
	int i = 0;
	printf("\n======== Table =======\n");
	printf("currentElements=%d maxElements=%d\n", table->currentElements, table->maxElements);
	for (i=0; i<table->currentElements; i++) {
		printf("%d: \"%s\" \"%s\"\n", i, table->array[i].name, (char*) table->array[i].value);
	}
	printf("======== End Table =======\n");
	return;
}

//
// It prints the elements in the array assuming the value is an int:
//
//======== Table =======
//currentElements=2 maxElements=10
//0: "George"               101
//1: "Peter"                245
//======== End Table =======
//
void rtable_print_int(RESIZABLE_TABLE * table)
{
	int i = 0;
	printf("\n======== Table =======\n");
	printf("currentElements=%d maxElements=%d\n", table->currentElements, table->maxElements);
	for (i=0; i<table->currentElements; i++) {
		printf("%d: \"%s\" %ld\n", i, table->array[i].name, (long) table->array[i].value);
	}
	printf("======== End Table =======\n");
	return;
}

//
// Adds one pair name/value to the table. If the name already exists it will
// Substitute its value. Otherwise, it will store name/value in a new entry.
// If the new entry does not fit, it will double the size of the array.
// The name string is duplicated with strdup() before assigning it to the
// table.
//
int rtable_add(RESIZABLE_TABLE * table, char * name, void * value) {
	//struct RESIZABLE_TABLE_ENTRY * tableEntry = NULL;
	// Find if it is already there and substitute value
	int i = 0;
	for(i = 0; i < table->currentElements; i++){
		if(!strcmp(table->array[i].name, name)){
			table->array[i].value = value;
			return 0;
		}
	}


	// Make sure that there is enough space
	if(table->maxElements == table->currentElements){
		RESIZABLE_TABLE_ENTRY * newArray = (RESIZABLE_TABLE_ENTRY *)
		malloc(table->maxElements*2*sizeof(RESIZABLE_TABLE_ENTRY));
		i = 0;
		for(; i < table->currentElements; i++)
			newArray[i] = table->array[i];
		table->array = newArray;
		table->maxElements = table->maxElements * 2;
	}

	//
	// Add name and value to a new entry.
	// We need to use strdup to create a copy of the name but not value.
	
	//printf("table->array: %d", table->array.value);
	i = table->currentElements;
	table->array[i].value = value;
	table->array[i].name = strdup(name);
	table->currentElements ++;
	return 0;
}

//
// Add name and value into table where value is a string (char *)
// Implement on top of rtable_add.
//
int rtable_add_str(RESIZABLE_TABLE * table, char * name, char * str_value)
{
	return rtable_add(table, name, (void *) strdup(str_value));
}

//
// Add name and value into table where value is a long
// Implement on top of rtable_add.
//
int rtable_add_int(RESIZABLE_TABLE * table, char * name, long int_value)
{
	return rtable_add(table, name, (void *) int_value );
}

//
// Returns the value that correspond to the name or NULL if the
// name does not exist in the table.
//
void * rtable_lookup(RESIZABLE_TABLE * table, char * name) {
	int i;
	for(i = 0; i < table->currentElements; i++){
		if(!strcmp(table->array[i].name, name))
			return table->array[i].value;
	}
	return NULL;
}

//
// It removes the entry with that name from the table. The entries after the entry
// removed will shift downwards. Also the name and value strings will be freed.
//
int rtable_remove(RESIZABLE_TABLE * table, char * name) {
	int i;
	for(i = 0; i < table->currentElements; i++){
		if(!strcmp(table->array[i].name, name)){
			//free(table->array[i].name);
			//free(table->array[i].value);

			for(; i < table->currentElements - 1; i++){
				table->array[i].name = table->array[i+1].name;
				table->array[i].value = table->array[i+1].value;
			}
			//free(table->array[i+1].name);
			//free(table->array[i+1].value);
			table->currentElements--;
			return 1;
		}
	}
	return 0;
}

//
// It returns in *name and *value the name and value that correspond to
// the ith entry. It will return 1 if successful, or 0 otherwise.
//
int rtable_get_ith(RESIZABLE_TABLE * table, int ith, char ** name, void ** value) {
	if(ith >= table->currentElements)
		return 0;
	*name = table->array[ith].name;
	*value = table->array[ith].value;
	return 1;
}

//
// It removes the ith entry from the table. The entries after the entry removed are
// moved downwards to use the empty space. Also the name/value strings are freed.
//
int rtable_remove_ith(RESIZABLE_TABLE * table, int ith) {
	if(ith >= table->currentElements)
		return 0;
	return rtable_remove(table, table->array[ith].name);
}

//
// It returns the number of elements in the table.
//
int rtable_number_elements(RESIZABLE_TABLE * table) {
	return table->currentElements;
}

//
// It returns the maximum number of elements in the table
//
int rtable_max_elements(RESIZABLE_TABLE * table) {
	return table->maxElements;
}

#define MAXLINE 512

//
// It saves the table in a file called file_name with values as char *. The format of the
// file is as follows:
//
// name1\n
// str1\n
// \n
// name2\n
// str2\n
// ...
//
// Notice that there is an empty line between each name/value pair.
//
int rtable_save_str(RESIZABLE_TABLE * table, char * file_name) {
	int i;
	FILE * fd = fopen(file_name, "w");
	if(fd == NULL)
		return 0;
	for(i = 0; i < table->currentElements; i++)
		fprintf(fd, "%s\n%s\n\n", (char*)table->array[i].name, (char *)table->array[i].value);
	fclose(fd);
	return 1;
}

//

//
int rtable_read_str(RESIZABLE_TABLE * table, char * file_name) {
	int i = i;
	char * lne;
	char * name = malloc(sizeof(char));
	char * value = malloc(sizeof(char));
	*name = '\0';
	*value = '\0';

	char c;
	int len = 0;
	int newLineCount = 0;
	char * tempStr = malloc(sizeof(char)*(MAXLINE));
	FILE * fd = fopen(file_name, "r");
	if(fd == NULL)
		return 0;
	//table = rtable_create();
	while((c = fgetc(fd)) != EOF){
		if(c == '\n'){
			newLineCount++;
			len = 0;
		}
		if ((c != '\n')&&((newLineCount % 3) == 0)){
			//store name character
			char * temp = malloc(sizeof(char)*(2));
			temp[0] = c;
			temp[1] = '\0';
			char * temp2 = malloc(sizeof(char)*(len+1));
			if(len)
			//insert parenthesis here
				temp2 = strdup(name);
			else
				temp2[0] = '\0';
			len++;
			name = malloc(sizeof(char)*(len+1));
			strcpy(name, temp2);
			strcat(name, temp);
			free(temp);
			free(temp2);
		}else if ((c != '\n')&&((newLineCount % 3) == 1)){
			//store value character
			char * temp = malloc(sizeof(char)*(2));
			temp[0] = c;
			temp[1] = '\0';
			char * temp2 = malloc(sizeof(char)*(len+1));
			if(len)
				temp2 = strdup(value);
			else
				temp2[0] = '\0';
			len++;
			value = malloc(sizeof(char)*(len + 1));
			strcpy(value, temp2);
			strcat(value, temp);
			free(temp);
			free(temp2);
		}else if((newLineCount % 3) == 0){
			//add name and value to table
			rtable_insert_last(table, name, value);
			//free memory
			//free(value);
			free(name);
		}
	}
	fclose(fd);
	return 1;
	/*
	char * name = malloc(sizeof(char) * MAXLINE);
	char * value = malloc(sizeof(char) * MAXLINE);
	int count = 0;
	FILE * fd = fopen(file_name, "r");
	if(fd == NULL)
		return 0;
	//table = rtable_create();

	while(((!(count++ % 2))? fscanf(fd, "%s", name): fscanf(fd, "%*s  %[^\n]", value)) != EOF){
		if((count - 1) % 2)
			rtable_add_str(table, name, strdup(value));
	}

	fclose(fd);
	return 1;
	*/
}

//
// It saves the table in a file called file_name with values as int. The format of the
// file is as follows:
//
// name1\n
// int1\n
// \n
// name2\n
// int2\n
// ...
//
// Notice that there is an empty line between each name/value pair.
//
int rtable_save_int(RESIZABLE_TABLE * table, char * file_name) {
        int i;
	FILE * fd = fopen(file_name, "w");
	if(fd == NULL)
		return 0;
	for(i = 0; i < table->currentElements; i++)
		fprintf(fd, "%s\n%ld\n\n", (char*)table->array[i].name, (long)table->array[i].value);
	fclose(fd);
	return 1;

}

//
// It reads the table from the file_name indicated asumiing that the values are
// char *. If the table already has entries, it will clear the entries.
//
int rtable_read_int(RESIZABLE_TABLE * table, char * file_name) {
        char * name = malloc(sizeof(char) * MAXLINE);
	long value = 0;
	int count = 0;
	FILE * fd = fopen(file_name, "r");
	if(fd == NULL)
		return 0;
	//table = rtable_create();
	while(((!(count++ % 2))? fscanf(fd, "%s", name): fscanf(fd, "%ld", &value)) != EOF){
		if((count - 1) % 2)
			rtable_insert_last(table, name, (void *)value);
	}
	fclose(fd);
	return 1;
}

//
// It sorts the table according to the name. The parameter ascending determines if the
// order si ascending (1) or descending(0).
int cmpfunc(RESIZABLE_TABLE_ENTRY *a, RESIZABLE_TABLE_ENTRY *b){
        return(strcmp(a->name, b->name));
}
int cmpfuncDes(const void * va, const void * vb){
	RESIZABLE_TABLE_ENTRY *a = (RESIZABLE_TABLE_ENTRY *) va;
	RESIZABLE_TABLE_ENTRY *b = (RESIZABLE_TABLE_ENTRY *) vb;
        return(cmpfunc(b, a));
}

int cmpfuncAs(const void * va, const void * vb){
	RESIZABLE_TABLE_ENTRY *a = (RESIZABLE_TABLE_ENTRY *) va;
	RESIZABLE_TABLE_ENTRY *b = (RESIZABLE_TABLE_ENTRY *) vb;
        return(cmpfunc(a, b));
}

void rtable_sort(RESIZABLE_TABLE * table, int ascending)
{
        if(ascending)
                qsort(table->array, table->currentElements, sizeof(RESIZABLE_TABLE_ENTRY), cmpfuncAs);
        else
                qsort(table->array, table->currentElements, sizeof(RESIZABLE_TABLE_ENTRY), cmpfuncDes);
}


//
// It sorts the table according to the name. The parameter ascending determines if the
// order si ascending (1) or descending(0).
//
int cmpfuncInt(RESIZABLE_TABLE_ENTRY *a, RESIZABLE_TABLE_ENTRY *b){
        return(a->value - b->value);
}

int cmpfuncDesInt(const void * va, const void * vb){
	RESIZABLE_TABLE_ENTRY *a = (RESIZABLE_TABLE_ENTRY *) va;
	RESIZABLE_TABLE_ENTRY *b = (RESIZABLE_TABLE_ENTRY *) vb;
        return(cmpfuncInt(b, a));
}

int cmpfuncAsInt(const void * va, const void * vb){
	RESIZABLE_TABLE_ENTRY *a = (RESIZABLE_TABLE_ENTRY *) va;
	RESIZABLE_TABLE_ENTRY *b = (RESIZABLE_TABLE_ENTRY *) vb;
        return(cmpfuncInt(a, b));
}

void rtable_sort_by_intval(RESIZABLE_TABLE * table, int ascending)
{
	if(ascending)
	        qsort(table->array, table->currentElements, sizeof(RESIZABLE_TABLE_ENTRY), cmpfuncAsInt);
	else
		qsort(table->array, table->currentElements, sizeof(RESIZABLE_TABLE_ENTRY), cmpfuncDesInt);
}

//
// It removes the first entry in the table.
// All entries are moved down one position.
// It also frees memory allocated for name and value.
//
int rtable_remove_first(RESIZABLE_TABLE * table) {
	if(table->currentElements == 0)
		return 0;
	rtable_remove_ith(table, 0);
	return 1;
}

//
// It removes the last entry in the table.
// It also frees memory allocated for name and value.
//
int rtable_remove_last(RESIZABLE_TABLE * table) {
	if(table->currentElements == 0)
		return 0;
	rtable_remove_ith(table, table->currentElements - 1);
	return 1;
}

//
// Insert a name/value pair at the beginning of the table.
// The entries are moved one position upwards.
// There is no check if the name already exists. The entry is added
// at the beginning of the table.
//
int rtable_insert_first(RESIZABLE_TABLE * table, char *name, void * value) {
        int i = 0;
	RESIZABLE_TABLE_ENTRY * newArray;
	if(table->maxElements == table->currentElements){
		newArray = (RESIZABLE_TABLE_ENTRY *)
		malloc(table->maxElements*2*sizeof(RESIZABLE_TABLE_ENTRY));
	}
	else{
		newArray = (RESIZABLE_TABLE_ENTRY *)
		malloc(table->maxElements*sizeof(RESIZABLE_TABLE_ENTRY));
	}
	for(; i < table->currentElements; i++)
		newArray[i + 1] = table->array[i];

	i = 0;
	table->array = newArray;
	table->array[i].value = value;
	table->array[i].name = strdup(name);
	table->currentElements ++;
	return 1;

}

//
// Insert a name/value pair at the end of the table.
// The entries are moved one position upwards.
// There is no check if the name already exists. The entry is added
// at the end of the table.
//
int rtable_insert_last(RESIZABLE_TABLE * table, char *name, void * value) {
	int i = 0;
	if(table->maxElements == table->currentElements){
                RESIZABLE_TABLE_ENTRY * newArray = (RESIZABLE_TABLE_ENTRY *)
        	malloc(table->maxElements*2*sizeof(RESIZABLE_TABLE_ENTRY));
        	i = 0;
        	for(; i < table->currentElements; i++)
                	newArray[i] = table->array[i];
                table->array = newArray;
		table->maxElements = table->maxElements * 2;
	}
	i = table->currentElements;
	table->array[i].value = value;
	table->array[i].name = strdup(name);
	table->currentElements ++;
	return 1;
}

