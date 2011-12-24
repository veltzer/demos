#!/usr/bin/groovy

/*
	A simple example of a myClosureure that does not have any arguments
	supplied to it. This myClosureure does not return anything.
*/

def myClosure = { println "hello!" }

println "Executing the Closure:"
myClosure()
