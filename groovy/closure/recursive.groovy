#!/usr/bin/groovy

/*
	An example of a recursive closure

	Notes:
	- call is actually a method of the closure object.
	- you cannot use 'this.call' since this is the object where the closure is used.
	- notice that using 'call' is preferred since it always calls the current closure
	without the need to refer to the name of the closure. Some closure do not have name
	(like if they are local variables) - that is also the reason I removed the 'def' from
	the two latter examples...
*/

// the standard way to write a recursive closure:
def fac = { int n -> n == 0 ? 1 : n * call(n - 1) }
println "Executing the Closure "+fac(5)

// another way to write the same thing:
fac2 = { int n -> n == 0 ? 1 : n * fac2(n - 1) }
println "Executing the Closure "+fac2(6)

// and another way to write the same thing:
fac3 = { int n -> n == 0 ? 1 : n * fac3.call(n - 1) }
println "Executing the Closure "+fac3(4)

/*
// another way to write the same thing:
def fac4 = { int n -> n == 0 ? 1 : n * delegate.fac4(n - 1) }
println "Executing the Closure "+fac4(6)
*/












