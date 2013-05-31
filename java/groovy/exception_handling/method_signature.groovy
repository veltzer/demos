#!/usr/bin/groovy

/*
	An example of functions/methods in Groovy that declare types
	of exception that they may throw
*/

// our own class for exception handling
class MyException extends Exception {
}

// the next definition means that function 'met' ** may ** throw the exception
// 'MyException'. It ** may ** also throw others as well.
def met() throws MyException {
	throw new RuntimeException('fubar')
}
try {
	met()
} catch(RuntimeException e) {
	println 'yes, we got an exception'
}

def met2() throws MyException,RuntimeException { 
	throw new RuntimeException('fubar')
}
try {
	met2()
} catch(RuntimeException e) {
	println 'yes, we got an exception'
}
