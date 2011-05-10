#!/usr/bin/groovy

/*
	A simple example of a closure that does not have any arguments
	supplied to it. This closure does not return anything.
*/

def clos = { println "hello!" }

println "Executing the Closure:"
clos()
