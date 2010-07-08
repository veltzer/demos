#include <stdio.h>

extern char *build_id;

void my_init(void) {
	// call the original...
	_init();
	fprintf(stderr, "I'm in my_init %s\n", __FILE__);
	fprintf(stderr, ".note.gnu.build-id is %s", .note.gnu.build - id);
}


void my_fini(void) {
	fprintf(stderr, "I'm in my_fini %s\n", __FILE__);
	// call the original...
	_fini();
}
