#include <stdio.h>
#include <unistd.h>
#include <sys/syscall.h>

/*
 * This example shows how to call exit from a C/C++ program. (exit(2)).
 * Your program never continues to the following line...
 */
int main(int argc, char **argv, char **envp) {
	// the following two ways of calling the _exit(2) syscall
	// are the same...
	syscall(SYS_exit);
	_exit(1);
	printf("Hello, World!\n");
	return(0);
}
