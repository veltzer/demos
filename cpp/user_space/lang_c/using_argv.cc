#include <stdio.h>
#include <string.h>

/*
 * This example shows that you can create a single binary that will behave
 * differently according to the name by which it is called. This trick is used
 * heavily in Linux. Examples are the fgrep, grep and egrep triplet, the busybox
 * implementation and more.
 *
 * 				Mark Veltzer
 */

int main(int argc, char **argv, char **envp) {
	if (strcmp(argv[0], "./add") == 0) {
		printf("Im adding");
	}
	if (strcmp(argv[0], "./sub") == 0) {
		printf("Im subtracting");
	}
	return(0);
}
