#!/usr/bin/groovy

/*
	Notice that:
	- you dont HAVE to declare a package name.
	- all class are by default public.
	- when running a class you run the 'main' static function of that class
*/

class SumItUp {
	static int sum(a,b) {
		a+b
	}
	static void main(args) {
		println sum(1,5)
		println sum(1,2)
	}
}
