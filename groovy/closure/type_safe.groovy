#!/usr/bin/groovy

/*
	An example of a type safe closure...
	The type of the argument must be an integer.
*/

def mySquare = { int x -> x*x }

println "Executing the Closure "+mySquare(7)

// now lets see what happens when we forget to pass the argument
// (if we had not stated that x is an 'int' then we would have gotten
// into the closure with x=null)...
try {
	println "Executing the Closure "+mySquare()
} catch(MissingMethodException e) {
	println 'yes, got an exception for forgetting the argument'
}
// lets try to call it with the wrong type...
try {
	println "Executing the Closure "+mySquare("fubar")
} catch(MissingMethodException e) {
	println 'yes, got an exception for the wrong type'
}
// lets try to call it with the wrong type...
try {
	println "Executing the Closure "+mySquare(7,9)
} catch(MissingMethodException e) {
	println 'yes, got an exception for wrong number of arguments'
}
