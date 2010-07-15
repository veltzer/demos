/*
 * This program demostrates the concept of sparse files...
 * Notice the following facts:
 * - this behaviour depends on the underlying file system and some brain
 * dead filesystems (like DOS) will not do any sparse thing.
 * - this bahaviour is DEFAULT in linux and unix on major supported filesystems.
 * - other pages are full of 0's.
 *
 * 	Mark Veltzer
 *
 * TODO:
 * - show that reading from the file from the begining is possible and produces 0's.
 */

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h> // for stat(2)
#include <sys/stat.h> // for stat(2)
#include <unistd.h> // for stat(2)

#include "us_helper.hh"

// this is the position we will seek to...
//const int pos=1024*1024;
const int pos = 1000000;
// this is the string to be printed into the file
const char *string = "hello";
// this is the name of the file to be used
const char *fname = "/tmp/my.sparse.file";

int main(int argc, char **argv, char **envp) {
	// this will be used to check return values
	int ret = 0;
	FILE *f = fopen(fname, "w");

	if (f == NULL) {
		perror("could not open file");
		exit(1);
	}
	ret = fseek(f, pos, SEEK_CUR);
	if (ret == -1) {
		perror("could not seek file");
		exit(1);
	}
	ret = fwrite(string, strlen(string), 1, f);
	if (ret == -1) {
		perror("could not read file");
		exit(1);
	}
	ret = fclose(f);
	if (ret == -1) {
		perror("could not close file");
		exit(1);
	}
	struct stat buf;
	scie(stat(fname, &buf),"stat");
	fprintf(stderr,"buf.size is %lu\n",buf.st_size);
	fprintf(stderr,"buf.blocks (512B each) is %lu\n",buf.st_blocks);
	fprintf(stderr,"real total calculated size is %lu\n",buf.st_blocks*512);
	my_system("ls -l %s",fname);
	my_system("du -h %s",fname);
	scie(unlink(fname),"unlink");
	return(0);
}
