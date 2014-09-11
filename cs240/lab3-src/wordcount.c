
#include <stdio.h>
#include <stdlib.h>
#include "resizable_table.h"

void
printUsage() {
	printf("Usage: wordcount [-w|-s] file\n");
}

int
main(int argc, char **argv) {
	if ( argc < 2) {
		printUsage();
		exit(1);
	}

	// Add your implementation here

	exit(0);
}


