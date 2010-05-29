This demo is all about doing arithmetic in the kernel.
======================================================
- If you compile on kernel 2.6.31-14-generic (ubuntu 9.10)
- If you compile on kernel 2.6.28-15-generic (ubuntu 9.04)
	you will find that the symbol __udivdi3 is missing.
	You see that in two place:
	- when you compile the module you get the warning:
	WARNING: "__udivdi3" [/home/mark/rafael/nu/demo/kernel_arithmetic/demo.ko] undefined!
	- when you try to insmod the module you get the error:
	[12697.177574] demo: Unknown symbol __udivdi3
	This means that you need to link with libgcc.
	just run 'make relink'.
