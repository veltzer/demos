#include <stdio.h>
#include <stdlib.h>    // for exit(3)
#include <stdio_ext.h> // for the buffer query functions
#include <string.h>

/*
 *      This example shows the different buffering schemes of the standard
 *      io files...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */
void printBuff(FILE *s, const char *name) {
	printf("stats for the [%s] stream\n", name);
	bool isLineBuf = (__flbf(s) > 0);
	if (isLineBuf) {
		printf("stream is line buffered\n");
	} else {
		printf("stream is not line buffered\n");
		size_t bufSize = __fbufsize(s);
		printf("size of buffer is %d\n", bufSize);
	}
}


int main(int argc, char **argv, char **envp) {
	const char *writeFileName = "/tmp/newfile.txt";
	const char *readFileName = "/etc/passwd";

	printBuff(stdin, "stdin");
	printBuff(stdout, "stdout");
	printBuff(stderr, "stderr");

	FILE *f1 = fopen(writeFileName, "w");
	if (f1 == NULL) {
		perror("unable to open file");
		exit(1);
	}
	printBuff(f1, "newly created file for writing");
	fclose(f1);
	remove(writeFileName);

	FILE *f2 = fopen(readFileName, "r");
	if (f2 == NULL) {
		perror("unable to open file");
		exit(1);
	}
	printBuff(f2, "newly created file for reading");
	fclose(f2);
	return(0);
}
