#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(){
        int len;
        FILE * fp;
        char * str;
        int year = 0;
        int count = 0;
        fp = fopen("file.txt", "w+");
        fputs("We\n12\n\nare\n13\n\nin\n2012\n\n", fp);
        rewind(fp);
	while((len = (count++ % 3)? fscanf(fp, "%s\n%d\n\n", str): fscanf(fp, "%d", &year)) != EOF)
		printf("I got here");
	fclose(fp);
	return(0);
}
