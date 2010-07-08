#include <stdio.h>
#include <string.h>
#include <sys/mman.h>
#include <unistd.h>

#include "us_helper.h"

/*
 *      This application demonstrates the use of anonymous memory mappings
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */
int main(int argc, char **argv, char **envp) {
	const int size = 1000000;
	void      *p;
	int       page_size;

	printproc(NULL);
	SCPE(p = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_POPULATE | MAP_ANONYMOUS, -1, 0), "doing anonymous mmap");
	printf("p is %p\n", p);
	// this next line needs permission to lock memory (check ulimit...)
	//SC(mlock(p,size),"mlock");
	printproc(NULL);
	SCIE(page_size = getpagesize(), "getpagesize");
	printf("page_size is %d\n", page_size);
	printf("p modulu page_size is %d\n", (unsigned int)p % page_size);
	memset(p, 0, size);
	printproc(NULL);
	return(0);
}
