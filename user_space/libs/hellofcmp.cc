#include <fcmp.h>
#include <stdio.h>

/*
 * EXTRA_LIBS=-I/local/tools/include -L/local/tools/lib -lfcmp
 */
int main(int argc, char **argv) {
	printf("first result is %d\n", fcmp(0.5, 0.7, 0.1));
	printf("second result is %d\n", fcmp(0.5, 0.500000001, 0.2));
	return(0);
}