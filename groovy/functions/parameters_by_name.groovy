#!/usr/bin/groovy

/*
	This example shows how to pass parameters by name to groovy functions
*/

// simple function
def substract(a,b) {
	a-b
}
// positional parameters
println substract(2,2)
// named parameters (in order)
println substract(a=5,b=3)
// named parameters (out of order) NASTY BUG
println substract(b=2,a=5)
// wrong names for parameters NASTY BUG
println substract(z=2,m=5)
