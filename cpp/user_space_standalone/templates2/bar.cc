#include "Foo.h"

/*
 * EXTRA_CMDS=pkg-config --cflags --libs ACE
 */

/*
 * This code does IMPLICIT instantiation of Foo<int>
 * It should be compiled WITHOUT any special flags
 * This is the default behaviour of gcc.
 */
void bar() {
	Foo<int> f;
	f.setT(5);
}
