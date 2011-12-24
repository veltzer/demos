#!/usr/bin/groovy

/*
	Some basic examples of lists...

	Notes:
	- the basic type which is created for a list is an array list (java.util.ArrayList).
	- you can create other types but the syntax sugar will be gone, gone, gone.
	- an empty list is created via the [] syntax.
	- pushing new elements can be done via the << operator
	- the list could be used in a foreach type loop.
*/

// dont HAVE to put the type here
List l=[]
println "l is ${l} and it's type is ${l.class}"
// pushing into the list
l << 'one'
l << 'two'
l << 'three'
println "l is ${l} and it's type is ${l.class}"
// cool methods...
println "l.join(',') is ${l.join(',')}"

// foreach loop
for(x in l) {
	println x
}
