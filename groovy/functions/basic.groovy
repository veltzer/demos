#!/usr/bin/groovy

/*
	Declaring functions in groovy...
*/

// most short, no return, no types
def sum1(a,b) {
	a+b
}
// most long, with return, with types (and with type checking)
int sum2(int a,int b) {
	return a+b
}
// weird, type check the first argument but not the second
def sum3(String a,b) {
	return a+b
}

println '4+5 is '+sum1(4,5)
println '6+7 is '+sum2(6,7)
println 'fu+7 is '+sum3('fu',7)

// lets pass wrong types to the first function (Duck Typing anyone ?)
println 'fu+bar is '+sum1('fu','bar')
// lets pass wrong types to the second function
try {
	println 'fu+bar is '+sum2('fu','bar')
} catch(e) {
	println 'yes, got an exception '+e
}
