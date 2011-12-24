#!/usr/bin/groovy

/*
	Exception handling in groovy is like in java except all exceptions
	are runtime (no checked exceptions). Some find that a very good idea...

	Note:
	You cannot leave the 'catch' parenthesis empty completely (in case you
	want to catch all exceptions and handle them without referring to the
	exception object or maybe get it via some automatic variable).
*/

// only catching our exception
try {
	// bad URL
	new URL('malformedUrl')
} catch(MalformedURLException e) {
	println "yes, we got an exception"
	println "exception is "+e
}

// catching 'Exception' which is the parent of all exceptions
try {
	// bad URL
	new URL('malformedUrl')
} catch(Exception e) {
	println "yes, we got an exception"
	println "exception is "+e
}

// groovy syntactic sugar (emitting the exception type means 'Exception')
try {
	// bad URL
	new URL('malformedUrl')
} catch(e) {
	println "yes, we got an exception"
	println "exception is "+e
}
