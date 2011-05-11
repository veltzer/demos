#!/usr/bin/groovy

/*
	Basic variable definition and usage

	Notes:
	- no automatic casing between strings and doubles
	- the 'GroovyCastException' is thrown
	- doubles to strings does work.
*/

import org.codehaus.groovy.runtime.typehandling.GroovyCastException

// without type declaration, can be anything
def x=5
println x
// with type declaration
try {
	double pi='3.14'
} catch(GroovyCastException e) {
	println 'got exception '+e
}
// with type declaration
try {
	String pi=3.14
	println "pi is ${pi} and it's type is ${pi.class}"
} catch(GroovyCastException e) {
	println 'got exception '+e
}
// combinding the two syntax elements above...
def double e=2.71
println 'e is '+e
