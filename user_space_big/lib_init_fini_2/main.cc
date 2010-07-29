#include <unistd.h> // for fork(2), _exit(2)
#include <sys/wait.h> // for waitpid(2)

#include "us_helper.hh"

/*
 *      This is an empty testing application
 */
int main(int argc, char **argv, char **envp) {
	TRACE("begin");
	// fork so that we will see if the hooks are called
	// for forked children too (constructors are not called, destructors are...)...
	// this example is for a well behaved child (exists ok...)
	TRACE("forking a well behaved child...");
	pid_t child_pid=fork();
	if(child_pid) {
		// parent
		int status;
		pid_t ret=waitpid(child_pid,&status,0);
	} else {
		// child
		TRACE("child starting");
		TRACE("child ending");
		return 0;
	}
	// lets do it again to see what happens if the child terminates abruptly... 
	TRACE("forking a brat...");
	child_pid=fork();
	if(child_pid) {
		// parent
		int status;
		pid_t ret=waitpid(child_pid,&status,0);
	} else {
		// child
		TRACE("child starting");
		TRACE("child ending");
		_exit(0);
	}
	TRACE("end");
	// lets do some multi-threading work to see how multi-threading is affecting all this...
	return(0);
}
