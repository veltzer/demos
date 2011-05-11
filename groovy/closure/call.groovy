#!/usr/bin/groovy

/*
	A closure is an object. It has a 'call' method that can be used
	to execute it's code and also pass arguments to it. This is
	especially usefull for varargs type invocations where the arguments
	are gotten from somewhere else...
*/

def myClosure = { println "hello!" }

println "Executing the Closure:"
myClosure.call()

def mySquare = { it*it }
println "Executing the Closure "+mySquare.call(7) 
