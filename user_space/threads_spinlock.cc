#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<pthread.h>

#include"us_helper.h"

/*
	This is a demo for thread spin locks.
	Run on a single CPU machine you will see a problem in that they will block
	each other.

			Mark Veltzer

EXTRA_LIBS=-lpthread
*/

pthread_spinlock_t mylock;
int val=0;

void* worker(void* p) {
	while(true) {
		TRACE("before lock");
		scig(pthread_spin_lock(&mylock),"pthread_spin_lock");
		sleep(1);
		scig(pthread_spin_unlock(&mylock),"pthread_spin_unlock");
		TRACE("after lock");
		sleep(1);
	}
	return NULL;
}

int main(int argc,char** argv,char** envp) {
	// first initialize the lock (no need for sharing between processes which
	// is the reason for the 0 in the second argument...)
	TRACE("initializing the lock...");
	scig(pthread_spin_init(&mylock,0),"pthread_spin_init");
	const int num=2;
	pthread_t threads[num];
	int ids[num];
	void* rets[num];
	TRACE("starting threads...");
	for(int i=0;i<num;i++) {
		ids[i]=i;
		scig(pthread_create(threads+i,NULL,worker,ids+i),"pthread_create");
	}
	TRACE("finished creating threads, joining them...");
	for(int i=0;i<num;i++) {
		scig(pthread_join(threads[i],rets+i),"pthread_join");
	}
	TRACE("joined all threads, destroying the lock...");
	scig(pthread_spin_destroy(&mylock),"pthread_spin_destroy");
	return 0;
}
