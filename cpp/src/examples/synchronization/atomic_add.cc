#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h> // for sysconf

#include "us_helper.hh"

/*
 *      This is a demo which shows atomic add using the
 *      __sync_add_and_fetch gcc function.
 *      see
 *      http://gcc.gnu.org/onlinedocs/gcc/Atomic-Builtins.html
 *      for more details...
 *
 *      The idea here is to try to break this function by having multiple
 *      thread try to increment the counter at once. This is achieved by
 *      using pthread barriers so this example is based on the pthread barriers example.
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=-lpthread
 */
pthread_barrier_t bar;
int counter = 0;
const int wait_usecs = 0;
//FILE *pfile = stdout;
FILE* pfile=stderr;
const int attempts=10000;
const int thread_num = 10;
const int cpu_num = sysconf(_SC_NPROCESSORS_ONLN);

void *worker(void *p) {
	int num = *(int *)p;

	fprintf(pfile, "starting thread %d\n", num);
	for (int i = 0; i < attempts; i++) {
		int ret;
		CHECK_ONEOFTWO(ret = pthread_barrier_wait(&bar),0, PTHREAD_BARRIER_SERIAL_THREAD);
		//fprintf(pfile, "thread %d got %d from pthread_barrier_wait\n", num, ret);
		//int b = __sync_add_and_fetch(&counter, 1);
		__sync_add_and_fetch(&counter, 1);
		//fprintf(pfile, "thread %d got %d from counter\n", num, b);
		usleep(wait_usecs);
		//fprintf(pfile, "thread %d finished work\n", num);
	}
	fprintf(pfile, "ending thread %d\n", num);
	return(NULL);
}


int main(int argc, char **argv, char **envp) {
	pthread_t threads[thread_num];
	pthread_attr_t attrs[thread_num];
	int ids[thread_num];
	cpu_set_t cpu_sets[thread_num];
	void           *rets[thread_num];

	CHECK_ZERO(pthread_barrier_init(&bar, NULL, thread_num));
	fprintf(pfile, "main starting\n");
	for (int i = 0; i < thread_num; i++) {
		ids[i] = i;
		CPU_ZERO(cpu_sets + i);
		CPU_SET(i % cpu_num, cpu_sets + i);
		//print_cpu_set(pfile,cpu_sets + i);
		CHECK_ZERO(pthread_attr_init(attrs + i));
		CHECK_ZERO(pthread_attr_setaffinity_np(attrs + i, sizeof(cpu_set_t), cpu_sets + i));
		CHECK_ZERO(pthread_create(threads + i, attrs + i, worker, ids + i));
	}
	fprintf(pfile, "main ended creating threads\n");
	for (int i = 0; i < thread_num; i++) {
		CHECK_ZERO(pthread_join(threads[i], rets + i));
	}
	CHECK_ZERO(pthread_barrier_destroy(&bar));
	fprintf(pfile,"counter is %d and should be %d\n",counter,thread_num*attempts);
	fprintf(pfile, "main ended\n");
	return(0);
}
