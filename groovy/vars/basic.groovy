#!/usr/bin/groovy

/*
	Basic variable definition and usage

	Notes:
	- no automatic casing between strings and doubles
	- the 'GroovyCastException' is thrown
	- doubles to strings does work.
	- can use def or not
*/

import org.codehaus.groovy.runtime.typehandling.GroovyCastException

// without type declaration, can be anything
def x=5
println x
// without 'def'
y=7
println y
// with type
double z=9
println z

// the next statements, if enabled, will generate a s COMPILE time exception (redefinition of variable)
//def x=8
//def z=3.4

// this, however, does not generate a compile time error since y was dynamic before
def y=10
println y

// with type declaration
try {
	double pi='3.14'
} catch(GroovyCastException e) {
	println 'no type conversion between string and double'
}
// with type declaration
try {
	String pi=3.14
	println "pi is ${pi} and it's type is ${pi.class}"
} catch(GroovyCastException e) {
	println 'got exception '+e
}
// combinding the two syntax elements from above...
def double e=2.71
println 'e is '+e
