#include<stdio.h>
#include<string.h>
#include<dialog.h>

/*
	This example shows how to use the dialog C library.
*/

int main(int argc,char** argv,char** envp) {
	init_dialog(NULL,NULL);
	end_dialog();
	return 0;
}
