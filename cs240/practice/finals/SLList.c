//This whole thing is wrong

#include "SLList.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
SLList * sllist_create(){
	SLList * sLList = (SLList *)malloc(sizeof(SLList));
	sLList->head = (SLListEntry *)malloc(sizeof(SLListEntry));
	sLList->head->next = sLList->head;
	return sLList;
}
/*
//inserts at the end of the list
// Insert an element in the linked list. If name already exists substitute value. Otherwise insert entry. Return 0 if name is not there.
int sllist_insert(SLList *sllist,  char * name, char * value) {
	SLListEntry * next = sllist->head->next; 

	while(next != sllist->head){
		if(!strcmp(next->name, name)){
			free(next->value);
			next->value = strdup(value);
			return 1;
		}
		next = next->next;
	}
	SLListEntry * en = (SLListEntry *) malloc(sizeof(SLListEntry));
	en->value = strdup(value);
	en->name = strdup(name);
	en->next = sllist->head;
	next->next = en;
	return 0;
}

int sllist_remove(SLList * sllist, char * name){
	SLListEntry * next = sllist->head->next;
	SLListEntry * previous = sllist->head;
	while(next != sllist->head){
		if(!strcmp(next->name, name)){
			previous->next = next->next;
			free(next->value);
			free(next->name);
			free(next);
			return 1;
		}
		previous = next;
		next = previous->next;
	}
	return 0;
}

int sllist_last(SLList * sllist, char ** pname, char ** pvalue){
	int rVal = 0;
	SLListEntry * next = sllist->head->next;
	while(next != sllist->head)
		rVal = 1;
	if(rVal){
		*pname = strdup(next->name);
		*pvalue = strdup(next->value);
	}
	return rVal;
}

void sllist_print(SLList * sllist){
	SLListEntry * next = sllist->head->next;
	int i = 0;
	while(next != sllist->head){
		printf("%d: name=%s value=%s\n", i++, next->name, next->value);
		next = next->next;
	}
}

void sllist_reverse(SLList * sllist){
	SLListEntry * next = sllist->head->next;
	while(next != sllist->head)
		next = next->next;
}
int main(){
	SLList * sllist = (SLList *) malloc(sizeof(SLList));
	
}*/
