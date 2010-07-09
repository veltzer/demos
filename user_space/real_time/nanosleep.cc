#include <stdio.h> // for printf(3)
#include <string.h>
#include <time.h>
#include <unistd.h>

#include "us_helper.hh"

/*
 *	This is an example showing how to sleep for very short periods of
 *	time and proves that it is indeed so...
 *
 *      As you can see there is a diff of about 100 micro between the time
 *      to sleep and the real time slept. This is MUCH less than the HZ
 *      value for linux (ranging from 100HZ to 1000HZ which yields 10000 micro
 *      to 1000 micro which are much bigger values.
 *
 *      In order to actually prove this there is still a need to split this
 *      program into two threads the higher priority of which runs this
 *      code and the lower keeping the CPU occupied all the time (by doing
 *      some calculation). According to expection the difference from the
 *      expected sleep time should remain about 100 micro.
 *
 *      Where does this 100 micro come from ? The answer lies in the accuracy
 *      of the interrupt handler, it's clock and the time to reschedule...
 *      Maybe the real time patch to the kernel can reduce this further...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=-lcpufreq
 */
int main(int argc, char **argv, char **envp) {
	//SC(nanosleep(100000),"nanosleep");
	//for(int i=1000;i<20000;i+=1000) {
	const int repeats = 100;

	for (int i = 100; i < 2000; i += 100) {
		unsigned long sum = 0;
		for (int j = 0; j < repeats; j++) {
			ticks_t start = getticks();
			//usleep(i);
			timespec t;
			t.tv_sec = 0;
			t.tv_nsec = 1000 * i;
			nanosleep(&t, NULL);
			ticks_t end = getticks();
			sum += get_mic_diff(start, end);
		}
		printf("time expired for usleep of %u micros is %lu diff is %lu\n", i, sum / repeats, sum / repeats - i);
	}
	return(0);
}