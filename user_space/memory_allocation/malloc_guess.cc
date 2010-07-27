#include <stdio.h>  // for printf(3)
#include <stdlib.h> // for malloc(3)
#include <assert.h> // for assert(3)
#include <sys/types.h> // for getpid(2)
#include <unistd.h> // for getpid(2)

/*
 * This application tries to guess where the next malloc allocation would be...
 *
 * In order to analyze malloc performance take a look at the "mguess_allocated"
 * function.
 * - It seems that malloc always needs at least 4 bytes for internal
 * book keeping. This is where it keeps the size of the block which is allocated
 * (probably - well check it later...).
 * - It is also deducible that malloc always allocated on an 8 byte boundry.
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 *
 * TODO:
 * - improve the guessing system to also handle free (get reports about them
 *   and be able to guess even though there are calls to free(3)).
 */

static char* p;
static bool debug=false;

/* let the guessing system know that you have allocated memory */
void mguess_allocated(unsigned int size) {
	unsigned int diff;
	if(size%8>4) {
		diff=size-size%8+16;
	} else {
		diff=size-size%8+8;
	}
	if(diff==8) diff+=8;
	if(debug) {
		printf("diff is %d, p is %p\n",diff-size,p);
	}
	p+=diff;
}

/* initialize the guessing system */
void mguess_init(void) {
	p=(char*)malloc(1);
	mguess_allocated(1);
}

/* get a guess about the next allocation from the guessing system */
void* mguess_guess(void) {
	return (void*)p;
}
/* shut down the guessing system */
void mguess_fini(void) {
	// do nothing...
}

int main(int argc, char **argv, char **envp) {
	mguess_init();
	// lets randomize things a bit so everything will be different on each run...
	srandom(getpid());
	// lets do some random allocatinons...
	for(unsigned int i=0;i<100;i++) {
		unsigned int size=random()%10000;
		void* g=mguess_guess();
		void* p=malloc(size);
		mguess_allocated(size);
		if(debug) {
			printf("g is %p, p is %p, size is %d\n",g,p,size);
		}
		assert(g==p);
	}
	mguess_fini();
	return(0);
}
