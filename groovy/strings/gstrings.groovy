#!/usr/bin/groovy

/*
	Some examples of GStrings interpolation...
*/

println "did you know that 2+2=${2+2}?"
l=['red','green','blue']
println "the length of the list is ${l.size()}"

// if the name of the variable is simple and there are no ambiguities
// the use can drop the curly braces...
myname='Mark'
println "my name is $myname"

// if methods have return values...
foxcolor = ['b', 'r', 'o', 'w', 'n']
println "The color is ${foxcolor.join()}"
