#!/usr/bin/groovy

/*
	Variables changing types
*/

import org.codehaus.groovy.runtime.typehandling.GroovyCastException

// without type declaration, can be anything
def x=5.0
println "x is ${x} and it's type is ${x.class}"
// lets change the type of x to 'String'...
x='fubar'
println "x is ${x} and it's type is ${x.class}"

// with type declaration
int y=5
println "y is ${y} and it's type is ${y.class}"
// lets change the type of y to 'String'...
try {
	y='fubar'
	println "y is ${y} and it's type is ${y.class}"
} catch(GroovyCastException e) {
	println 'yes, got an exception '+e
}
// second (instanceof) is better for subclassing...
assert y.class==Integer
assert y instanceof Integer
if(y instanceof Integer) {
	println 'yes, using instanceof'
}
if(y.class==Integer) {
	println 'yes, using =='
}
