#include <stdio.h>
#include <string.h>
#include <sys/utsname.h>

#include "us_helper.h"

/*
 *      This is a demo showing the sizes of variables on the architecture
 *      it is running on (it also prints the name of the architecture).
 *
 *      This is intended for a GNU/linux system.
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */
#define PRINT_SIZEOF(astr, a) printf("size of " astr " is %d\n", sizeof(a));

int main(int argc, char **argv, char **envp)
{
	struct utsname buf;

	scie(uname(&buf), "get uname");
	printf("architecture is [%s]\n", buf.machine);
	PRINT_SIZEOF("bool", bool);
	PRINT_SIZEOF("char", char);
	PRINT_SIZEOF("unsigned char", unsigned char);
	PRINT_SIZEOF("short", short);
	PRINT_SIZEOF("unsigned short", unsigned short);
	PRINT_SIZEOF("int", int);
	PRINT_SIZEOF("unsigned int", unsigned int);
	PRINT_SIZEOF("long", long);
	PRINT_SIZEOF("unsigned long", unsigned long);
	PRINT_SIZEOF("long long", long long);
	PRINT_SIZEOF("unsigned long long", unsigned long long);
	PRINT_SIZEOF("float", float);
	PRINT_SIZEOF("double", double);
	return(0);
}
