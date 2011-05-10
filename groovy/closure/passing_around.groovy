#!/usr/bin/groovy

/*
	Passing closures to methods/functions is possible (and even recommended)...
*/

// a closure that squares things
def square = { it*it }

def funcSum(l,c) {
	def newList = []
	l.collect(newList,c);
	return newList
}

def list = [5, 6, 7, 8]
resultList=funcSum(list,square);
println resultList
