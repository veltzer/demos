#!/usr/bin/groovy

/*
	This example shows how to pass parameters by name to groovy functions
*/

// simple function
def subtract(a,b) {
	a-b
}
// positional parameters
println subtract(2,2)
// named parameters (in order)
println subtract(a=5,b=3)
// named parameters (out of order) NASTY BUG
println subtract(b=2,a=5)
// wrong names for parameters NASTY BUG
println subtract(z=2,m=5)
