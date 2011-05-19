#include<stdio.h>
#include<stdlib.h>

const int size=100;

int main(int argc,char** argv,char** envp) {
	char cmd[size];
	fgets(cmd,size,stdin);
	printf("%s",cmd);
	return(0);
}
