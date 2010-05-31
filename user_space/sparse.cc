#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include<string.h>

/*
 * This program demostrates the concept of sparse files...
 * see the result using ?
 * du -s my.sparse
 * ls -l my.sparse
 */

// this is the position we will seek to...
//const int pos=1024*1024;
const int pos=1000000;
// this is the string to be printed into the file
const char* string="hello";
// this is the name of the file to be used
const char* fname="my.sparse";

int main(int argc,char** argv,char** envp) {
	// this will be used to check return values
	int ret=0;
	FILE* f=fopen(fname,"w");
	if(f==NULL) {
		perror("could not open file");
		exit(1);
	}
	ret=fseek(f,pos,SEEK_CUR);
	if(ret==-1) {
		perror("could not seek file");
		exit(1);
	}
	ret=fwrite(string,strlen(string),1,f);
	if(ret==-1) {
		perror("could not read file");
		exit(1);
	}
	ret=fclose(f);
	if(ret==-1) {
		perror("could not close file");
		exit(1);
	}
	return 0;
}
