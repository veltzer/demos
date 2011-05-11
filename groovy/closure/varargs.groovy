#!/usr/bin/groovy

/*
	This is an example of a closure with variable arguments
*/

def square = { it*it }

def funcSum(l,c) {
	def newList = []
	l.collect(newList,c)
	return newList
}

def list = [5, 6, 7, 8]
resultList=funcSum(list,square)
println resultList
