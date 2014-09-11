
#include <stdlib.h>
#include "mystring.h"

int mystrlen(char * s) {
	int c = 0;
	while((s[c++]) != '\0');
	return --c;
}

char * mystrcpy(char * dest, char * src) {
        int i = 0;
        for(; src[i] != '\0'; dest[i] = src[i++]);
	dest[i] = '\0';
	return dest;
}

char * mystrcat(char * dest, char * src) {
        int i = mystrlen(dest);
	int j = 0;
	for(; src[j] != '\0'; dest[i++] = src[j++]);
	dest[i] = '\0';
	return dest;
}

int mystrcmp(char * s1, char * s2) {
	int i = 0;
	int j = 1; //continue while loop

	int returnValue;
	if (mystrlen(s1) > mystrlen(s2))
	    returnValue = 1;
	else if (mystrlen(s1) < mystrlen(s2))
	    returnValue = -1;
	else
	    returnValue = 0;

	while(j && (s1[i] != '\0')){
            if(s1[i] < s2[i]){
                returnValue = -1;
	        j = 0;
	    }
	    else if(s1[i] > s2[i]){
 		returnValue = 1;
		j = 0;
	    }
	    else
	        i++;
	}
	return returnValue;
}

char * mystrstr(char * hay, char * needle) {
	int i = 0; //needle looper
	int j = 0; //hay looper
	int h = 0; //index of first match
	/*if(mystrlen(needle) == 0)
	    return hay;
	while(hay[j] != '\0'){
	    if(hay[j] == needle[i]){
	        h = j;
		while(needle[i] != '\0'){
		    if((hay[j] == needle[i]&&(hay[j++] != '\0')))
		        i++;
	      	    else
		    	return NULL;
		}
		return hay += h; 
	    }
	    j++;
	}
	*/
	for(; i <= mystrlen(hay); i++){
	    char substr[mystrlen(needle)+ 1];
	    strncpy(substr
	    if (mystrcmp(
	}

	return NULL;
}

char * mystrdup(char * s) {
	return NULL;
}

char * mymemcpy(char * dest, char * src, int n)
{
        
	return NULL;
}

