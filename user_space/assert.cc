//#define NDEBUG
#include<assert.h> // for assert(3)

/*
	Demo the use of assert in C/C++

	* note that there is also an assert_perror macro and that all of the asserts
	can be turned off using "#define NDEBUG" before including assert.h.

		Mark Veltzer

EXTRA_LIBS=
*/

int main(int argc,char** argv,char** envp) {
	assert(1==2);
	return 0;
}
