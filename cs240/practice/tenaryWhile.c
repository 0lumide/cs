#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(){
	int len;
	FILE * fp;
	char * str = malloc(sizeof(char) * 100);
	int year = 0;
	int count = 0;

	fp = fopen("file.txt", "w+");
	fputs("We\n12\n\nare\n13\n\nin\n2012\n\n", fp);
	rewind(fp);
	
	while((len = (!(count++ % 2))? fscanf(fp, "%s\n", str): fscanf(fp, "%d\n\n", &year)) != EOF){
		printf("I got here\n");
		if(!((count - 1) % 2))
			printf("%s\n", str);
		else
			printf("%d\n", year);
	}
	fclose(fp);
	return(0);
}
