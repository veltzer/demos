#include<stdio.h>
#include<stdlib.h>

int main(int argc,char** argv,char** envp) {
	char cmd[256];
	sprintf(cmd,"/home/veltzer/interbit/material/unix_fundamentals/examples/c_runner/all_but_first.pl < %s > /tmp/a.c",argv[1]);
	system(cmd);
	system("gcc -o /tmp/a.out /tmp/a.c");
	system("/tmp/a.out");
	return(0);
}
