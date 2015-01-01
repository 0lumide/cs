#include <stdio.h>
#include <stdlib.h>

int main(){
	char * s = (char *)malloc(2);
	s[0] = 'a';
	s[1] = 0;
	void * v = (void *)s;
	printf("%s\n", (char*)v);

	int d = 3;
	void * dv = (void *)&d;
}
