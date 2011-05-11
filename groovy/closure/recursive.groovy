#!/usr/bin/groovy

/*
	An example of a recursive closure
*/

def fac = { n -> n == 0 ? 1 : n * call(n - 1) }

println "Executing the Closure "+fac(5)
