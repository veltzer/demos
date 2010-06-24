#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

/*
 *    This demo is to be used when demoing nice level.
 *    Run a couple of these processes and loot at the output.
 *    TODO: This example should be improved to set it's own nice level via APIs and
 *    fork on it's own so that no user intervention should be needed....
 */
int main(int argc, char **argv, char **envp)
{
	pid_t mypid = getpid();
	int   i     = 0;

	while (1)
	{
		if (i % 10000000 == 0)
		{
			printf("%d: I'm alive with counter %d!\n", mypid, i);
		}
		i++;
	}
	return(0);
}
