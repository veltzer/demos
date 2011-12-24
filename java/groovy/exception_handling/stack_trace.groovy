#!/usr/bin/groovy

/*
	Exception handling in groovy is like in java except all exceptions
	are runtime (no checked exceptions). Some find that a very good idea...
*/

import org.codehaus.groovy.runtime.StackTraceUtils 

try {
	throw new RuntimeException("fubar")
} catch(RuntimeException e) {
	println "yes, we got an exception"
	e.printStackTrace()
	println "Wasnt that a long stack trace ?"
}
