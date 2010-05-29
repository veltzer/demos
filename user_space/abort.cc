#include<stdlib.h> // for abort(3)

#include"us_helper.h"

/*
	This example explores the abort(3) function call.

	After this program terminates echo $? and see that it's termination
	code is 134.

	TODO: show that by catching the signal we cannot avoid abort terminating
	the program...

				Mark Veltzer
EXTRA_LIBS=
*/

int main(int argc,char** argv,char** envp) {
	abort();
	return 0;
}
