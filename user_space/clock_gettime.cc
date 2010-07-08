#include <stdio.h>
#include <string.h>
#include <time.h>

#include "us_helper.hh"

/*
 *      Demo the clock_gettime usage
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=-lrt
 */
void print_timespec(const char *msg, struct timespec *ts) {
	printf("%s %ld %ld\r", msg, ts->tv_sec, ts->tv_nsec);
	fflush(stdout);
}


int main(int argc, char **argv, char **envp) {
	//clockid_t clk_id=CLOCK_REALTIME;
	clockid_t clk_id = CLOCK_MONOTONIC;
	struct timespec res;

	scig(clock_getres(clk_id, &res), "clock_getres");
	print_timespec("clock resolution is ", &res);
	printf("\n");
	while (true) {
		struct timespec t;
		scig(clock_gettime(clk_id, &t), "clock_gettime");
		print_timespec("clock time is ", &t);
	}
	return(0);
}
