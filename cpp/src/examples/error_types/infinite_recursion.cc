#include <stdio.h> // for printf(3)
#include <sys/time.h> // for getrusage(2)
#include <sys/resource.h> // for getrusage(2)

#include "us_helper.hh"

/*
 * This is an example of infinite recursion prevention using setting the stack size
 *
 * Notes:
 * We really have to force the compilers hand into calling the function again and again
 * since we compiler this module in optimized mode.
 *
 * three ways to do this:
 * - put the noinline attribute on the function. This is no good since the compiler
 *	will respect this when other functions call this function but not when this function
 *	calls itself. We could have used this with mutual recursion.
 * - have the function do some side effect. This has the same problems as above. Again,
 *	mutual recursion could have helped.
 * - have the function call itself several times. This saved the day for this example.
 *
 *		Mark Veltzer
 */

void show_stack_size() {
	static unsigned int print_num=0;
	struct rusage usage;
	CHECK_NOT_M1(getrusage(RUSAGE_SELF,&usage));
	printf("print_num is %d\n",print_num);
	printf("usage.ru_maxrss=%lu\n",usage.ru_maxrss);
	//printf("usage.ru_ixrss=%lu\n",usage.ru_ixrss);
	//printf("usage.ru_idrss=%lu\n",usage.ru_idrss);
	//printf("usage.ru_isrss=%lu\n",usage.ru_isrss);
	printf("usage.ru_minflt=%lu\n",usage.ru_minflt);
	//printf("usage.ru_majflt=%lu\n",usage.ru_majflt);
	print_num++;
}

static int myfunc(int y,bool end) {
	// this is also not required (sometimes it forces the compiler not to inline since
	// there could be side effects)
	//asm ("");
	if(y%10000==0) {
		printf("y is %d\n",y);
		show_stack_size();
	}
	if(end && y==1000000) {
		return y;
	}
	int z=myfunc(++y,end);
	// without this second call gcc would implement a goto instead of calling the function
	// again!!! - smart ass compiler...
	z=myfunc(++y,end);
	return z;
}
// this is not required - the double call above solves the smartass compiler problem.
//int myfunc(int y) __attribute__((noinline));

int main(int argc,char** argv,char** envp) {
	myfunc(0,false);
	return EXIT_SUCCESS;
}
