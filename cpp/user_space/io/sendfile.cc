#include <stdio.h> // for printf(3), perror(3)
#include <errno.h> // for perror(3)
#include <stdlib.h> // for exit(3)
#include <sys/types.h> // for open(2)
#include <sys/stat.h> // for open(2)
#include <fcntl.h> // for open(2)
#include <sys/sendfile.h> // for senffile(2)
#include <unistd.h> // getpagesize(2)

#include "us_helper.hh"

/*
 * This demos shows how to use sendfile(2) to avoid copy to/from user space.
 *
 * The demo itself is just a simple version of cp(1)
 *
 * It seems that this demo doesn't run properly on 2.6.32 and should run
 * on 2.6.33 or later.
 * On 2.6.38 it works well.
 *
 * Notes:
 * - we send the data page by page and call sendfile(2) many times. This could
 *   be avoided using fstat(2) after the open of the input file and sending the
 *   entire content at once.
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */

int copy_file(const char* filein, const char* fileout) {
	size_t sendfile_bufsize=getpagesize();
	int err_code=0;
	int fdin,fdout;
	sc(fdin=open(filein, O_RDONLY, 0666));
	sc(fdout=open(fileout, O_WRONLY|O_CREAT|O_TRUNC, 0666));
	int ret;
	while((ret=sendfile(fdout,fdin,NULL,sendfile_bufsize))>0) {
	}
	if(ret==0) {
		//printf("I seem to have reached EOF\n");
	} else { // ret<0
		perror("unable to send file");
		err_code=1;
	}
	sc(close(fdin));
	sc(close(fdout));
	return err_code;
}

int main(int argc, char **argv, char **envp) {
	if(argc!=3) {
		printf("usage: %s [infile] [outfile]\n",argv[0]);
		exit(1);
	}
	const char* filein=argv[1];
	const char* fileout=argv[2];
	return copy_file(filein,fileout);
}
