#!/usr/bin/groovy

/*
	Declaring functions in groovy...
*/

import org.codehaus.groovy.runtime.typehandling.GroovyCastException

// most short, no return, no types, no safety...
def sum1(a,b) {
	a+b
}
// most long, with return, with types (and with type checking)
int sum2(int a,int b) {
	return a+b
}
// weird, type check the first argument but not the second
// no type check for the return value
def sum3(String a,b) {
	return a+b
}

println '4+5 is '+sum1(4,5)
println '6+7 is '+sum2(6,7)
println 'fu+7 is '+sum3('fu',7)

// lets pass wrong types to the first function (Duck Typing anyone ?)
println 'fu+bar is '+sum1('fu','bar')
// lets pass wrong types to the second function
try {
	println 'fu+bar is '+sum2('fu','bar')
} catch(e MissingMethodException) {
	println 'yes, got exception for sending the wrong type argument to sum2'
}

// now lets try to have a method which returns something different than what it says...
String giveMeAString() {
	return 7
}
// this works because of automatic conversion...
println giveMeAString()

// lets try the reverse
int giveMeAnInt() {
	return 'sdfsdf'
}
// this will throw an exception
try {
	println giveMeAnInt()
} catch(GroovyCastException e) {
	println 'always return what you declare'
}
