#!/usr/bin/groovy

/*
	The type of a closure
*/

def clos = { println "hello!" }

// as you can see the closure is some sort of inner class of the entire class where it
// is defined...
println clos

// the owner of the closure is the class in which it is defined...
println clos.owner
