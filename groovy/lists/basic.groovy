#!/usr/bin/groovy

/*
	Some basic examples of lists...

	Notes:
	- the basic type which is created for a list is an array list.
	- you can create other types but the syntax sugar will be gone, gone, gone.
*/

l=[]
println "l is ${l} and it's type is ${l.class}"
// pushing into the list
l << 'one'
l << 'two'
l << 'three'
println "l is ${l} and it's type is ${l.class}"
# cool methods...
println "l.join(',') is ${l.join(',')}"
