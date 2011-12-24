#!/usr/bin/groovy

/*
	This is an example of how to add methods to a class that you did not write...
	In this case we add methods to the Integer class

	Notes:
	- Inside the closure which is attached to the metaClass class the 'delegate' automatic
	variable is the instance on which the method is called.
	- 'this' is the closure object itself.
*/


Integer x=5
println x

try {
	println x.minusOne()
} catch(MissingMethodException e) {
	println 'no, the minusOne method does not exist (yet)'
}
println x.metaClass
x.metaClass.minusOne={ return delegate-1 }
println x.minusOne()

Integer.metaClass.plusOne={ return delegate+1 }
try {
	println x.plusOne()
} catch(MissingMethodException e) {
	println 'no, x doesnt have pluse One'
}
Integer y=5
println y.class
println y.plusOne()
