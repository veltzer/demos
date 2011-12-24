#!/usr/bin/groovy

/*
	This shows how to create lists of sequential numbers

	Syntax is
	l=from..to

	Notes:
	- notice that the type of the list is groovy.lang.IntRange and NOT
	java.util.ArrayList. This type pretends to be a list but is stored
	in a much more efficient manner.
	- this means that it is efficient to do l=1..1000000 (in terms of memory
	footprint)
*/

Range l=1..5
println "l is ${l} and it's type is ${l.class}"

// foreach loop
for(x in l) {
	println x
}

// lets try to add an element to a range
try {
	// intentionally try to add an element which is 'easier' to add
	// to the range...
	l << 6
} catch(UnsupportedOperationException e) {
	println 'nope, cannot add elements to ranges'
}

// cool join method
println l.join(',')
