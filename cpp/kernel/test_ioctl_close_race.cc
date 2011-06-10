#include <stdio.h> // for fprintf(3), fflush(3)
#include <unistd.h> // for sleep(3), close(2)
#include <pthread.h> // for pthread_create(3), pthread_join(3)
#include <sys/types.h> // for open(2)
#include <sys/stat.h> // for open(2)
#include <fcntl.h> // for open(2)
#include <sys/ioctl.h> // for ioctl(2)

#include "us_helper.hh"

/*
 *      This application has two threads:
 *              - one running like crazy doing ioctls.
 *              - one asking to close the driver (which halts).
 *              - main asking for input and releases the close process...
 *
 *      What do we learn from this?
 *      - closing a file descriptor only schedules release to be called in the kernel.
 *      - this release will be called once any ioctls on the current file descriptor end.
 *      - any new operations on this file descriptor are not allowed (bad file descriptor).
 *
 *      		Mark Veltzer
 *
 * EXTRA_LIBS=-lpthread
 */

// file descriptor
int d, d2;

void *function_crazy(void *p) {
	bool over = false;
	int counter = 0;
	bool err = false;

	while (!over) {
		counter++;
		// ioctl to do nothing...
		int res = ioctl(d, 0, NULL);
		if (res == -1) {
			err = true;
			//perror("ERROR from crazy thread...");
			//over=true;
		} else {
		}
		if (counter % 10000 == 0) {
			char c;
			if (err) {
				c = 'E';
			} else {
				c = '.';
			}
			fprintf(stdout, "%c", c);
			fflush(stdout);
			err = false;
		}
	}
	return(NULL);
}


void *function_long(void *p) {
	SC(ioctl(d, 1, NULL));
	SC(ioctl(d, 1, NULL));
	return(NULL);
}


void *function_long2(void *p) {
	SC(ioctl(d2, 2, NULL));
	return(NULL);
}


void *function_close(void *p) {
	fprintf(stderr, "close thread started and going to sleep for two seconds\n");
	sleep(2);
	fprintf(stderr, "close thread trying to close handle...\n");
	SC(close(d));
	//SC(ioctl(d,0,NULL));
	return(NULL);
}


int main(int argc, char **argv, char **envp) {
	// file to be used
	const char *filename = "/dev/drv_ioctl_close_race";

	SC(d = open(filename, O_RDWR));
	SC(d2 = open(filename, O_RDWR));

	//pthread_t thread_crazy;
	//SCIG(pthread_create(&thread_crazy,NULL,function_crazy,NULL),"pthread crazy created");
	pthread_t thread_long;
	SCIG(pthread_create(&thread_long, NULL, function_long, NULL), "pthread long created");
	pthread_t thread_long2;
	SCIG(pthread_create(&thread_long2, NULL, function_long2, NULL), "pthread long created");
	pthread_t thread_close;
	SCIG(pthread_create(&thread_close, NULL, function_close, NULL), "pthread close created");

	//waitkey("press any key to wake the close thread up");
	//SC(ioctl(d,5,NULL));

	//SCIG(pthread_join(thread_crazy,NULL),"join");
	SCIG(pthread_join(thread_long, NULL), "join");
	SCIG(pthread_join(thread_long2, NULL), "join");
	SCIG(pthread_join(thread_close, NULL), "join");
	return(0);
}
