#include <stdio.h>
#include <wchar.h>
#include <string.h>
#include <locale.h>

#include <time.h>

/*
 *      This example shows how to use the gcc __DATE__ and related macros...
 *      This actually shows how to get the epoch of the compilation time of the current
 *      compilation unit...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 */

#include "us_helper.h"

int main(int argc, char **argv, char **envp) {
	printf("date is %s\n", __DATE__);
	printf("time is %s\n", __TIME__);
	struct tm tm;
	scpe(strptime(__DATE__ " " __TIME__, "%b %d %Y %H:%M:%S", &tm), NULL);
	time_t t = mktime(&tm);
	printf("t is %lu\n", t);
	return(0);
}
