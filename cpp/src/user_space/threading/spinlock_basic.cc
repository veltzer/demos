#include <pthread.h> // for pthread_spin_init/lock/unlock/destroy(3),
// for pthread_create(3), pthread_join(3)

#include "us_helper.hh" // for TRACE()

/*
 *      This is a demo for thread spin locks.
 *      Run on any machine and you will see a problem in that they will block
 *      each other. This blockage does not turn into a dead lock since the OS
 *      will preempt them and eventually each will get it's turn.
 *
 *                      Mark Veltzer
 *
 * EXTRA_LIBS=-lpthread
 */

static pthread_spinlock_t mylock;

static void *worker(void *p) {
	for(int i=0;i<10;i++) {
		//TRACE("before lock");
		scig(pthread_spin_lock(&mylock), "pthread_spin_lock");
		TRACE("sleeping");
		sleep(1);
		scig(pthread_spin_unlock(&mylock), "pthread_spin_unlock");
		//TRACE("after lock");
		sleep(1);
	}
	return(NULL);
}


int main(int argc, char **argv, char **envp) {
	// first initialize the lock (no need for sharing between processes which
	// is the reason for the 0 in the second argument...)
	TRACE("initializing the lock...");
	scig(pthread_spin_init(&mylock, 0), "pthread_spin_init");
	const int num = 2;
	pthread_t threads[num];
	int ids[num];
	TRACE("starting threads...");
	for (int i = 0; i < num; i++) {
		ids[i] = i;
		scig(pthread_create(threads + i, NULL, worker, ids + i), "pthread_create");
	}
	TRACE("finished creating threads, joining them...");
	for (int i = 0; i < num; i++) {
		scig(pthread_join(threads[i], NULL), "pthread_join");
	}
	TRACE("joined all threads, destroying the lock...");
	scig(pthread_spin_destroy(&mylock), "pthread_spin_destroy");
	return(0);
}
