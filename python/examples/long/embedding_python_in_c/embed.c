#include <Python.h>
int main(int argc,char** argv,char** envp) {
	Py_Initialize();
	PyRun_SimpleString(
		"print \"Hello, World!\"\n"
		"import time\n"
		"time.sleep(6)\n"
		"print \"Woke up!\"\n"
	);
	Py_Finalize();
	return 0;
}
