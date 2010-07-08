#include <stdlib.h> // for abort(3), atexit(3), exit(3), _Exit(2)
#include <unistd.h> // for _exit(2)
#include <sys/syscall.h> // for syscall(2)

#define DO_DEBUG
#include "us_helper.hh"

/*
 *      This example explores the abort(3) function call.
 *
 *      After this program terminates echo $? and see that it's termination
 *      code is 134.
 *
 *      TODO: show that by catching the signal we cannot avoid abort terminating
 *      the program...
 *
 *                              Mark Veltzer
 * EXTRA_LIBS=
 */

class A {
	public:
		~A() {
			printf("im in the destructor\n");
		}
}
void my_atexit(void) {
	DEBUG("in here");
}

int main(int argc, char **argv, char **envp) {
	SCIE(atexit(my_atexit),"atexit");
	const unsigned int code=5;
	A a;
	while(true) {
		printf("Please select your preferred way to die:\n");
		printf("1) abort(3).\n");
		printf("2) exit(3).\n");
		printf("3) _exit(2).\n");
		printf("4) _Exit(2).\n");
		printf("5) syscall(SYS_exit).\n");
		int a=getchar();
		switch(a) {
			case 1:
				abort();
			case 2:
				exit(code);
			case 3:
				_exit(code);
			case 4:
				_Exit(code);
			case 5:
				syscall(SYS_exit);
			default:
				printf("I dont know what you mean by '%d'...\n",a);
				break;
		}
	}
	printf("does this ever get executed ?!?\n");
	return(0);
}
