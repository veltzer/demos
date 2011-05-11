#!/usr/bin/groovy

/*
	Basic variable definition and usage
*/

// without type declaration, can be anything
def x=5
println x
// with type declaration
try {
	double pi='3.14'
} catch(e) {
	println 'got exception '+e
}
// combinding the two syntax elements above...
def double e=2.71
println 'e is '+e
