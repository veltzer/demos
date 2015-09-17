#include<stdio.h>	// for fgets(3), stdin, feof(3), printf(3), perror(3)
#include<stdlib.h>	// for EXIT_FAILURE, EXIT_SUCCESS

const int size=100;

void show_prompt() {
	printf("> ");
}

int main(int argc,char** argv,char** envp) {
	char cmd[size];
	char* ret;
	int sys_ret;
	show_prompt();
	ret=fgets(cmd,size,stdin);
	if(ret==NULL) {
		perror("problem with fgets");
		return EXIT_FAILURE;
	}
	while(!feof(stdin)) {
		sys_ret=system(cmd);
		if(sys_ret==-1) {
			perror("problem with system(3)");
			return EXIT_FAILURE;
		}
		show_prompt();
		ret=fgets(cmd,size,stdin);
		if(ret==NULL) {
			perror("problem with fgets");
			return EXIT_FAILURE;
		}
	}
	return EXIT_SUCCESS;
}
