#include <stdarg.h> // for va_start(3), va_end(3)
#include <syslog.h> // for vsyslog(3)

#include "us_helper.hh"

/*
 * This is a demo of how to do a full tracer, with:
 * - compiler builtins.
 * - backtracing.
 * - syslog (via vsyslog).
 * - indented messages.
 *
 * In this example I will use syslog defined priorities so as not to build a convesion
 * mechanism from my own priority levels to syslog priorities.
 *
 * 					Mark Veltzer
 *
 * TODO:
 * - add backtracing.
 * - add indentation.
 * - make all the logging go through one call to vsyslog.
 */

inline void my_trace(int level,const char* file,const char* base_file, const int line, const char* func, const char* function, const char* pretty_function,const char* fmt,...) {
	// the current implemention is quite simplistic...
	syslog(level,"level is %d\n",level);
	syslog(level,"file is %s\n",file);
	syslog(level,"base_file is %s\n",base_file);
	syslog(level,"line is %d\n",line);
	syslog(level,"func is %s\n",func);
	syslog(level,"function %s\n",function);
	syslog(level,"pretty_function is %s\n",pretty_function);
	// now lets print the actual message...
	va_list args;
	va_start(args, fmt);
	vsyslog(level, fmt, args);
	va_end(args);
}
inline void my_trace(int level,const char* file,const char* base_file,const int line, const char* func, const char* function, const char* pretty_function,const char* fmt,...) __attribute__((format(printf, 8, 9)));

// now wrap this up to get the right compiler macros...
// this is a pre-processor varargs type of tracer...
#define MY_TRACE(level,fmt,args...) my_trace(level,__FILE__,__BASE_FILE__,__LINE__,__func__,__FUNCTION__,__PRETTY_FUNCTION__,fmt, ## args)

class A {
	public:
		void thisMethod(int a, int b) {
			MY_TRACE(LOG_DEBUG,"did you know that %d+%d=%d?\n",a,b,a+b);
		}
};

int main(int argc, char **argv, char **envp) {
	A a;
	a.thisMethod(5,6);
	return 0;
}
