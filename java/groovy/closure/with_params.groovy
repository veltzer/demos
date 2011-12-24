#!/usr/bin/groovy

/*
	A closure that uses parameters that are assumed to be passed to it.
*/

def printSum = { a,b -> println a+b }

printSum(5,7)
