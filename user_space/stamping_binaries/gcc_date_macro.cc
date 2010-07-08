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

#include "us_helper.hh"

#define __stringify_1(x) # x
#define __stringify(x)   __stringify_1(x)

#define STRING_VERSION "1.23.56"
#define NUMERIC_VERSION 1.23.56

// the static allows us to use a compiled on tag for each file so you can put it in a common
// header and get stamping for all files in your project.
static const char* __attribute__((used)) date="date=" __FILE__ " " __stringify(__LINE__) " "__DATE__ " " __TIME__;
static const char* __attribute__((used)) string_version="string_version=" STRING_VERSION;
static const char* __attribute__((used)) numeric_version="numeric_version=" __stringify(NUMERIC_VERSION);

int main(int argc, char **argv, char **envp) {
	printf("date is %s\n", __DATE__);
	printf("time is %s\n", __TIME__);
	struct tm tm;
	scpe(strptime(__DATE__ " " __TIME__, "%b %d %Y %H:%M:%S", &tm), NULL);
	time_t t = mktime(&tm);
	printf("t is %lu\n", t);

	// lets try to run strings(1) on our own binary to see the data...
	const unsigned int len=1024;
	char cmd[len];
	snprintf(cmd,len,"strings %s | grep =",argv[0]);
	scie(system(cmd),"system");
	return(0);
}
