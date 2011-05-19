#!/usr/bin/groovy

/*
	Passing closures to methods/functions is possible (and even recommended)...
*/


def funcSum(l,c) {
	def newList = []
	l.collect(newList,c)
	return newList
}

def list = [5, 6, 7, 8]

// a closure that squares things
def square = { it*it }
resultList=funcSum(list,square)
println resultList

resultList2=funcSum(list,{ it-1 })
println resultList2

resultList3=funcSum(list) {
	it+2
}
println resultList3
