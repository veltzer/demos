#!/usr/bin/groovy

/*
	A closure that returns something is amazingly like a function/method...
	Compare to the same defined as a regular function/method...
*/

def closureSum = { a,b -> return a+b }

c=closureSum(5,7)
println 'c is '+c

def funcSum(a,b) { return a+b }

c=funcSum(5,7)
println 'c is '+c
