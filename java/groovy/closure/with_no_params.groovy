#!/usr/bin/groovy

/*
	Every closure gets one param if no param is explictly defined...
*/

// the next closure gets one param (it) and does not do anything with it...
def myClosure = { println "Hello" }
// this means that I can pass a param (no exception)
myClosure("fubar")

// if you really want a closure with no params...
def myClosure2 = { -> println "Hello" }
// now we'll get an exception
try {
	myClosure2("fubar")
} catch(MissingMethodException e) {
	println "yes, we got an exception, which is good..."
}
