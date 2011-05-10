#!/usr/bin/groovy

/*
	The type of a myClosureure
*/

def myClosure = { println "hello!" }

// as you can see the myClosureure is some sort of inner class of the entire class where it
// is defined...
println myClosure

// the owner of the myClosureure is the class in which it is defined...
println myClosure.owner
