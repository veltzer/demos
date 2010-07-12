#include <stdlib.h>
#include <stdio.h>

/*
 * This executable analyzes and tells you what happened to the child...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */
int main(int argc, char **argv, char **envp) {
	int res = atoi(argv[1]);
	printf("analyzing code %d\n",res);

	if (WIFSIGNALED(res)) {
		printf("ahm... Child was killed by os with signal %d\n", WTERMSIG(res));
	}
	if (WIFEXITED(res)) {
		printf("Child was NOT killed by OS.\n");
		int return_code = WEXITSTATUS(res);
		if (return_code) {
			printf("Child exited successfully but reported error %d\n", return_code);
		} else {
			printf("Child was a success\n");
		}
	}
	return(0);
}
