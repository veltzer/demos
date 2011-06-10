#include <sched.h> // for sched_yield(2)
#include <stdlib.h> // for abort(3)

// not defining the next symbol will cause much more use of your CPU
//#define DO_SCHED_YIELD

int main(int argc,char** argv,char** envp) {
	while(1) {
#ifdef DO_SCHED_YIELD
		int ret=sched_yield();
		if(ret==-1) {
			perror("could not sched_yield(2)");
			abort();
		}
#endif // DO_SCHED_YIELD
	}
	return(0);
}
