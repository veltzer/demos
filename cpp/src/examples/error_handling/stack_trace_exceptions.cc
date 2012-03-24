#include <iostream>
#include <stdlib.h>
#include <execinfo.h>
#include <exception>
#include "demangle.hh"

/*
 * This is a C++ example of how to create exceptions which contain a stack trace
 * of where they occured. This uses the backtrace function.
 * Notes:
 * - if you compile with -O2 you get less stack frames in the output
 * since some of the functions are inlined by the compiler.
 * - I added __attribute__((noinline)) to prevent the inlining to see more
 * stack traces. This made the stack frames visible but without the names.
 * If you want to see all stack frames then remove all optimization flags at
 * compile time.
 * - we drop two stack frames that don't interest
 * us (two functions that are above the 'main' function).
 * - yet another issue is C++ name mangling. You can either do that on the command
 * line using the 'c++filt(1)' tool or in the code by using the C++ library.
 * - the '-rdynamic' flag is needed if the 'backtrace_symbols' function is to
 * work and return symbols to you. 
 *
 * 			Mark Veltzer
 *
 * EXTRA_LIBS=-rdynamic
 *
 * TODO:
 * - show how to see the two "nameless" stack frames in the middle (they are not currently seen).
 * - bring back the c++ demangling code (it was removed because it was crashing the app).
 */

class TracedException : public std::exception {
private:
	// how many frames to drop from the stack frame
	// setting this to 0 will show you functions above 'main'
	// that you are probably not interested in
	static const int drop_frames = 2;
	static const int size = 25;
	void* array[size];
	int nSize;
	char** symbols;
public:
	TracedException() {
		nSize = backtrace(array, size);
		symbols = backtrace_symbols(array, nSize);
	}


	// TODO: need to C++'ize the next two methods
	void release() {
		free(symbols);
	}

	void print() {
		for (int i = nSize - drop_frames - 1; i >= 0; i--) {
			char *symbol = symbols[i];
			std::cerr
				<< symbol << ","
				<< std::endl;
			//char result_name[256];
			//char result_offset[256];
			//error_demangle(symbol, result_name, 256, result_offset, 256);
			//std::cerr
			//	<< symbol << ","
			//	<< result_name << ","
			//	<< result_offset
			//	<< std::endl;
		}
	}
};

void low_func() __attribute__((noinline));
void low_func() {
	throw TracedException();
}

void mid_func() __attribute__((noinline));
void mid_func() {
	low_func();
}

void top_func() __attribute__((noinline));
void top_func() {
	mid_func();
}

int main(int argc, char **argv, char **envp) {
	try {
		top_func();
	} catch (TracedException e) {
		e.print();
	}
	return(0);
}
