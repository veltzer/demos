#include<stdio.h> // for printf(3)
#include<stdlib.h> // for EXIT_SUCCESS

int main(int argc,char** argv,char** envp) {
	char c=27;
	char c2='[';
	char c3='1';
	char c4='m';
	printf("%c%c%c%cHello\n",c,c2,c3,c4);
	return EXIT_SUCCESS;
}
