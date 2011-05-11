#!/usr/bin/groovy

/*
	Assertion example in Groovy

	Notes:
	- if we replace 'Throwable' with 'Exception' then the 'catch' block
	will NOT get executed since 'Exception' does not cover assertions while
	'Throwable' does (higher up the hierarchy...).
	- Notice the nice assetion printout...:)
*/

try {
	assert 1==2
} catch(Throwable e) {
	println "yes, we got an exception"
	println "exception is "+e
}
