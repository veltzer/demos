#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc,char** argv,char** envp) {
	pid_t mypid=getpid();
	int i=0;
	while(1) {
		if(i%10000000==0) {
			printf("%d: I'm alive with counter %d!\n",mypid,i);
		}
		i++;
	}
	return 0;
}
