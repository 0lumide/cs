
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "linked_list.h"

//
// It returns a new LINKED_LIST. It allocates it dynamically,
// and initializaes the values. The initial list is empty.
//
LINKED_LIST * llist_create() {

	LINKED_LIST * list = (LINKED_LIST *) malloc(sizeof(LINKED_LIST));
	if (list == NULL) {
		return NULL;
	}

	// Create Sentinel node. This node does not store any data
	// but simplifies the list implementation.
	list->head = (LINKED_LIST_ENTRY *) malloc(sizeof(LINKED_LIST_ENTRY));
	if (list->head == NULL) {
		return NULL;
	}


	list->nElements = 0;
	list->head->next = list->head;
	list->head->previous = list->head;

	return list;
}

//
// It prints the elements in the alist in the form:
//
//===== List =====
//currentElements=2 maxElements=10
//0: Name: "George"       "23 Oak St, West Lafayette, 47906"
//1: Name: "Peter"        "27 Oak St, West Lafayette, 47906"
//======== End List =======
//
void llist_print(LINKED_LIST * list) {

	LINKED_LIST_ENTRY * e;

	printf("===== List =====\n");
	printf("nElements=%d\n", list->nElements);

	e = list->head->next;
	while (e != list->head) {
		printf("name=\"%s\" value=\"%s\"\n", e->name, e->value);
		e = e->next;
	}
	printf("======== End List =======\n");
}

//
// Adds one pair name/value to the list. If the name already exists it will
// Substitute its value. Otherwise, it will store name/value in a new entry.
// The name/vale strings are duplicated with strdup() before adding them to the
// list.
//
int llist_add(LINKED_LIST * list, char * name, char * value) {
	LINKED_LIST_ENTRY *e;

	e = list->head;
	while(e->next != list->head) {
		if(!strcmp(e->next->name, name)){
			e->next->value = strdup(value);
			return 1;
		}
		e = e->next;
	}
	e->next = (LINKED_LIST_ENTRY *) malloc(sizeof(LINKED_LIST_ENTRY));
	e->next->next = list->head;
	e->next->previous = e;
	list->head->previous = e->next;
	list->nElements++;
	e->next->name = strdup(name);
	e->next->value = strdup(value);
	return 1;
}

//
// Returns the value that correspond to the name or NULL if the
// name does not exist in the list.
//
char * llist_lookup(LINKED_LIST * list, char * name) {
	LINKED_LIST_ENTRY *e;

	e = list->head;
	while(e->next != list->head){
		if(!strcmp(e->next->name, name))
			return e->next->value;
		e = e->next;
	}
	return NULL;
}

//
// It removes the entry with that name from the list.
// Also the name and value strings will be freed.
//
int llist_remove(LINKED_LIST * list, char * name) {
	LINKED_LIST_ENTRY * e;
	LINKED_LIST_ENTRY * x;

	e = list->head;
	while(e->next != list->head){
		if(!strcmp(e->next->name, name)){
			x = e->next;
			free(x->name);
			free(x->value);
			x->next->previous = e;
			e->next = e->next->next;
			free(x);
			list->nElements--;
			return 1;
		}
		e = e->next;
	}
	return 0;
}

//
// It returns in *name and *value the name and value that correspond to
// the ith entry. It will return 1 if successful, or 0 otherwise.
//
int llist_get_ith(LINKED_LIST * list, int ith, char ** name, char ** value) {

	if(ith > list->nElements)
		return 0;

	LINKED_LIST_ENTRY * e;
	int i = 0;
	e = list->head;
	while(e->next != list->head){
		if(i == ith){
			*name = e->next->name;
			*value = e->next->value;
			return 1;
		}
		e = e->next;
		i++;
	}
}

LINKED_LIST_ENTRY * llist_get_ith_ad(LINKED_LIST * list, int ith){
	if(ith > list->nElements)
		return NULL;

	LINKED_LIST_ENTRY * e;
	int i = 0;
	e = list->head;
	while(e->next != list->head){
		if(i == ith){
			return e->next;
		}
		e = e->next;
		i++;
	}

}
//
// It removes the ith entry from the list.
// Also the name/value strings are freed.
//
int llist_remove_ith(LINKED_LIST * list, int ith) {
	LINKED_LIST_ENTRY * e;
	LINKED_LIST_ENTRY * x;

	int i = 0;
	e = list->head;
	while(e->next != list->head){
		if(i == ith){
			x = e->next;
			free(x->name);
			free(x->value);
			x->next->previous = e;
			e->next = e->next->next;
			free(x);
			list->nElements--;
			return 1;
		}
		e = e->next;
		i++;
	}
	return 0;
}

//
// It returns the number of elements in the list.
//
int llist_number_elements(LINKED_LIST * list) {
	return list->nElements;
}


//
// It saves the list in a file called file_name. The format of the
// file is as follows:
//
// name1\n
// value1\n
// \n
// name2\n
// value2\n
// ...
//
// Notice that there is an empty line between each name/value pair.
//
int llist_save(LINKED_LIST * list, char * file_name) {
	FILE * fd = fopen(file_name, "w");
	if(fd == NULL)
		return 0;

	LINKED_LIST_ENTRY * e;

	e = list->head;
	while(e->next != list->head){
		fprintf(fd, "%s\n%s\n\n", e->next->name, e->next->value);
		e = e->next;
	}
	fclose(fd);
	return 1;
}

//
// It reads the list from the file_name indicated. If the list already has entries, it will
// clear the entries.
//
char * removeNewline(char *src){
	char * dest;
	char * temp = malloc(sizeof(char)*(strlen(src)));
	strncpy(temp, src, strlen(src)-1);
	dest = strdup(temp);
	return dest;
}

int llist_read(LINKED_LIST * list, char * file_name) {
	FILE * fd = fopen(file_name, "r");
	if(fd == NULL)
		return 0;
	int maxline = 512;
	LINKED_LIST_ENTRY * e;
	char * str = malloc(sizeof(char) * maxline);
	char * value;
	char * name;
	int lineCount = 0;

	while(llist_remove_ith(list, 0)){}
	e = list->head;

	while(fgets(str, maxline, fd) != NULL){
		if(!(lineCount % 3))
			name = removeNewline(str);
		else if(lineCount % 3 == 1)
			value = removeNewline(str);
		else
			llist_add(list, name, value);
		lineCount++;
	}
	fclose(fd);
	return 1;
}
//
// It sorts the list according to the name. The parameter ascending determines if the
// order si ascending (1) or descending(0).
//
void llist_sort(LINKED_LIST * list, int ascending) {
	LINKED_LIST_ENTRY * n; //next entry
	LINKED_LIST_ENTRY * e;
	int notSorted = 1;
	int i;

	while(notSorted){
		notSorted = 0;
		i = 0;
		e = llist_get_ith_ad(list, i);//list->head->next;
		while(e->next != list->head){
			n = e->next;
			if( ascending?(strcmp(n->name, e->name) < 0): (strcmp(e->name, n->name) < 0)){
				//switch
				n->previous = e->previous;
				e->next = n->next;
				n->next->previous = e;
				n->next = e->previous->next;
				e->previous->next = n;
				e->previous = n;//(llist_get_ith_ad(list, i))->next;

				notSorted = 1;
			}
			e = llist_get_ith_ad(list, ++i);
		}
	}
}

//
// It removes the first entry in the list.
// All entries are moved down one position.
// It also frees memory allocated for name and value.
//
int llist_remove_first(LINKED_LIST * list) {
	return llist_remove_ith(list, 0);
}

//
// It removes the last entry in the list
// It also frees memory allocated for name and value.
//
int llist_remove_last(LINKED_LIST * list) {
	return llist_remove_ith(list, list->nElements -1);
}

//
// Insert a name/value pair at the beginning of the list.
// There is no check if the name already exists. The entry is added
// at the beginning of the list.
//
int llist_insert_first(LINKED_LIST * list, char *name, char * value) {
	LINKED_LIST_ENTRY *e;

	e = list->head->next;
	e->previous = (LINKED_LIST_ENTRY *) malloc(sizeof(LINKED_LIST_ENTRY));
	e->previous->next = e;
	e->previous->previous = list->head;
	list->head->next = e->previous;
	list->nElements++;
	e->previous->name = strdup(name);
	e->previous->value = strdup(value);
	return 1;

}

//
// Insert a name/value pair at the end of the list.
// There is no check if the name already exists. The entry is added
// at the end of the list.
//
int llist_insert_last(LINKED_LIST * list, char *name, char * value) {
	LINKED_LIST_ENTRY *e;

	e = list->head->previous;
	while(e->next != list->head) {
		e = e->next;
	}
	e->next = (LINKED_LIST_ENTRY *) malloc(sizeof(LINKED_LIST_ENTRY));
	e->next->next = list->head;
	e->next->previous = e;
	list->head->previous = e->next;
	list->nElements++;
	e->next->name = strdup(name);
	e->next->value = strdup(value);
	return 1;

}

