#!/usr/bin/groovy

/*
	Some basic examples of dictionaries...

	Notes:
	- m.class is null for maps
	- use m.getClass() to see the class.
	- could be used as the target of a foreach type loop

*/

Map m=[
	'Paris':'France',
	'Jerusalem':'Israel',
]

println "m is ${m} and it's type is ${m.class}"

println m.getClass()

// foreach loop
for(x in m) {
	println "The capital of ${x.value} is ${x.key}"
}
