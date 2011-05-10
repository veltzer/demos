#!/usr/bin/groovy

/*
	When the last element to a method/function is a closure you don't have to put it inside
	the parenthesis...
*/

def list = [5, 6, 7, 8]
def newList = []
list.collect(newList) { it*it }
println newList
// same as...
def newList2 = []
list.collect(newList2,{ it*it })
println newList2
