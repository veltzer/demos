#!/usr/bin/groovy

/*
	This is an example of a Meta Object Protocol implemented in Groovy
*/

def foo(d) {
	print d['hello']
}
d={
	hello 5
	foo 8
}
print foo(d) 
