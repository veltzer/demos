/*
 *	This example shows how to stamp binaries so that you will be able to know exactly who
 *	made them, when, on which machine, what version of software, from what source file and
 *	made what version was used.
 *	- to get the time stamp we use gcc's __DATE__ __TIME__ __FILE__ __LINE__ and related macros.
 *	- the demo also shows how to actually print the version from your program if you want to.
 *	- the demo shows how to stamp EACH compilation unit separately so that you will know what
 *	  version of each individual object file is.
 *	- the demo shows how to put stuff in the stamp whether they be strings, numbers or whatever.
 *	- the demo actually demonstrates that the information got to the object or exe file using
 *	  various techniques.
 *
 *		Mark Veltzer
 *
 *	TODO:
 *		- add checksum example with the relevant makefile fragment
 *
 * EXTRA_LIBS=
 */

#include <stdio.h> // for snprintf(3), printf(3)
#include <time.h> // for strptime(3), mktime(3)
#include "us_helper.hh"

#define __stringify_1(x) # x
#define __stringify(x) __stringify_1(x)

#define STRING_VERSION "1.23.56"
#define NUMERIC_VERSION 1.23.56

// this macro does two things:
// - makes sure that the compiler does not issue "unused variable" or tried to make
// the static variable go away just because no one is using it.
// - make sure that our data goes into it's own section of the object code.
#define SECTION ".compile_info"
#define ATTR __attribute__((section(SECTION),used))

// the static allows us to use a compiled on tag for each file so you can put it in a common
// header and get stamping for all files in your project.
static const char* ATTR date="date=" __FILE__ " " __stringify(__LINE__) " "__DATE__ " " __TIME__;
static const char* ATTR string_version="string_version=" STRING_VERSION;
static const char* ATTR numeric_version="numeric_version=" __stringify(NUMERIC_VERSION);

int main(int argc, char **argv, char **envp) {
	printf("date is %s\n", __DATE__);
	printf("time is %s\n", __TIME__);
	struct tm tm;
	scpe(strptime(__DATE__ " " __TIME__, "%b %d %Y %H:%M:%S", &tm), NULL);
	time_t t = mktime(&tm);
	printf("t is %lu\n", t);

	const unsigned int len=1024;
	char cmd[len];
	
	// lets try to run strings(1) on our own binary to see the data...
	snprintf(cmd,len,"strings %s | grep =",argv[0]);
	scie(system(cmd),"system");
	
	// lets try to run objdump(1) on our own binary to see the data...
	snprintf(cmd,len,"objdump -h %s | grep " SECTION,argv[0]);
	scie(system(cmd),"system");

	// lets try to see the actual symbols using objdump(1)...
	snprintf(cmd,len,"objdump --section=" SECTION " --source %s",argv[0]);
	scie(system(cmd),"system");
	return(0);
}
