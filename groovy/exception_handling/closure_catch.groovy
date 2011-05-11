#!/usr/bin/groovy

/*
	An example of a closure throwing an exception.
	Notice that the stack trace does have the correct line
	numbers that throw the exception.
*/

def myClosure={
	try {
		assert 1==2
	} catch(Throwable e) {
		println "yes, we got an exception"
		println "exception is "+e
		//e.printStackTrace()
	}
}
myClosure()
