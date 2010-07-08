#include <syslog.h>

int main(int argc, char **argv, char **envp) {
	const int a = 2, b = 2;
	int c = a + b;

	openlog("mark", LOG_PID, LOG_USER);
	for (int i = 0; i < 200; i++) {
		syslog(LOG_ERR, "did you know that %d+%d=%d?", a, b, c);
	}
	closelog();
	return(0);
}
