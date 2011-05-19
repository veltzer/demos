#include<stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc,char** argv,char** envp) {
	const char* buf="hello";
	int fd;
	fd=open("mysparsefile",O_RDWR | O_CREAT);
	if(fd==-1) {
		perror("cannot open");
		return 1;
	}
	lseek(fd,1000000000,SEEK_CUR);
	write(fd,(void*)buf,5);
	close(fd);
	return 0;
}
