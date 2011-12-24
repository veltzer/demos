#!/usr/bin/groovy

/*
	A 'real' closure. One that refers to the environment that it is in.
	How does this work? What if x was a real object, with lots of RAM
	requirements?
*/

def adder(x) {
	def myClosure = { y -> x+y }
	return myClosure
}

def add5=adder(5)
def add3=adder(3)
println add5
println add3
println add5(7)
println add3(8)
println add5.delegate
println add3.delegate
