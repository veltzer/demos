#!/usr/bin/groovy

/*
*/

// lets import the 'A' module...

import A

println A.myStaticMethod(2,2)
try {
	println A.myNonStaticMethod(2,2)
} catch(MissingMethodException e) {
	println 'yes, got exception for a missing method'
}

println A.myStaticPrivateMethod(2,2)
