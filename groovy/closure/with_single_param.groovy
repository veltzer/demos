#!/usr/bin/groovy

/*
	This shows that a closure with one param could either state
	it's name explicitly or use the automatic variable it.
*/

def printOne = { a -> println "Hello "+a }
def printTwo = { println "Hello "+it }

printOne("WorldOne");
printTwo("WorldTwo");
