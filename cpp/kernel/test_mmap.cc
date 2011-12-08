#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <unistd.h>

#include "us_helper.hh"

/*
 *      This is a test for the mmap demo:
 *      0. open.
 *      1. allocate memory in kernel space and get pointer in used (mmap).
 *      2. ask kernel to manipulate the memory.
 *      3. print it out.
 *      4. unmap.
 *      5. close.
 */

bool do_play = false;
bool do_stress = false;
bool do_vma = true;

void print_data(void *data, int size) {
	int msize;

	if (size < 256) {
		msize = size;
	} else {
		msize = 256;
	}
	char *pdata = (char *)malloc(msize);
	strncpy(pdata, (char *)data, msize - 1);
	pdata[msize - 1] = '\0';
	fprintf(stderr, "data is [%s]\n", pdata);
	free(pdata);
}


int main(int argc, char **argv, char **envp) {
	// the data pointer
	void *data;
	// another data pointer
	void *data2;
	// file to be used
	const char *filename = "/dev/demo";
	// file descriptor
	int d;
	// flags
	const int flags = MAP_PRIVATE | MAP_POPULATE;
	// the size of data that we need
	const int size = 1000000;
	// at what offset
	const int offset = 0;

	fprintf(stderr, "Asking the kernel to open the handle\n");
	SC(d = open(filename, O_RDWR));
	//printproc();

	SCPE(data = mmap(
	             NULL,                                                                                                                                                                                                                                                                                                                                                     /* we DO NOT recommend an address - better to let the kernel decide */
	             size,                                                                                                                                                                                                                                                                                                                                                     /* the size we need */
	             PROT_READ | PROT_WRITE,                                                                                                                                                                                                                                                                                                                                   /* we want read AND write */
	             flags,                                                                                                                                                                                                                                                                                                                                                    /* we don't want page faults */
	             d,                                                                                                                                                                                                                                                                                                                                                        /* file descriptor */
	             offset                                                                                                                                                                                                                                                                                                                                                    /* offset */
	             ), "mmap");
	SCPE(data2 = mmap(
	             NULL,                                                                                                                                                                                                                                                                                                                                                      /* we DO NOT recommend an address - better to let the kernel decide */
	             size,                                                                                                                                                                                                                                                                                                                                                      /* the size we need */
	             PROT_READ | PROT_WRITE,                                                                                                                                                                                                                                                                                                                                    /* we want read AND write */
	             flags,                                                                                                                                                                                                                                                                                                                                                     /* we don't want page faults */
	             d,                                                                                                                                                                                                                                                                                                                                                         /* file descriptor */
	             offset                                                                                                                                                                                                                                                                                                                                                     /* offset */
	             ), "mmap");
	fprintf(stderr, "pointer I got is %p\n", data);
	print_data(data, size);
	printproc("demo");

	fprintf(stderr, "Putting some data in the buffer...\n");
	memset(data, 'a', size);
	//strncpy((char*)data,"here is some data",size);
	print_data(data, size);

	if (do_play) {
		for (char i = 'a'; i < 'z'; i += 2) {
			fprintf(stderr, "Setting memory to ['%c']\n", i);
			memset(data, i, size);
			print_data(data, size);
			SC(ioctl(d, 1, NULL));
			print_data(data, size);
			waitkey(NULL);

			SC(ioctl(d, 2, i + 1));
			print_data(data, size);

			fprintf(stderr, "Asking kernel to read memory...\n");
			SC(ioctl(d, 1, NULL));
			print_data(data, size);
			waitkey(NULL);
		}
	}
	if (do_stress) {
		for (char i = 'a'; i < 'z'; i += 2) {
			memset(data, i, size);
			SC(ioctl(d, 1, NULL));
			SC(ioctl(d, 2, i + 1));
			SC(ioctl(d, 1, NULL));
		}
	}
	if (do_vma) {
		SC(ioctl(d, 0, data));
		void *p = (void *)((char *)data + size / 2);
		SC(ioctl(d, 0, p));
		waitkey(NULL);
	}

	SC(munmap(data, size));
	printproc("demo");
	SC(munmap(data2, size));
	printproc("demo");
	SC(close(d));
	return(0);
}
