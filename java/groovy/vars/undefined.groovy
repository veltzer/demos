#!/usr/bin/groovy

/*
	What if you don't define a variable?

	Notes:
	- the first example is good. Try to use something which isn't defined
	and you get an exception.
*/

try {
	print x
} catch(MissingPropertyException e) {
	println 'yes, got an exception '+e
}
x=5
// lets try it again...
try {
	println x
} catch(MissingPropertyException e) {
	println 'yes, got an exception '+e
}

def y=6
// lets try it again...
try {
	println y
} catch(MissingPropertyException e) {
	println 'yes, got an exception '+e
}
