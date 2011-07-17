#include <stdio.h> // for printf(3)

/*
 * This shows how to use the pre-processor to catenate stuff...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */

#define catit(a,b) a ## b
#define __stringify_1(x) # x
#define __stringify(x)   __stringify_1(x)

int main(int argc, char **argv, char **envp) {
	printf("This is the addition of %s and %s = %s\n",
		__stringify(foo),
		__stringify(bar),
		__stringify(catit(foo,bar))
	);
	return(0);
}
