#include<stdio.h>
#include<stdlib.h>

const int size=100;

void show_prompt() {
	printf("> ");
}

int main(int argc,char** argv,char** envp) {
	char cmd[size];
	show_prompt();
	fgets(cmd,size,stdin);
	while(!feof(stdin)) {
		system(cmd);
		show_prompt();
		fgets(cmd,size,stdin);
	}
	return(0);
}
