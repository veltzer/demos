#!/usr/bin/groovy

/*
	This example explores re-defintion of variables in groovy...
*/

def x=5
// the next line will cause a ** Compile ** time error if unremarked...
// surrounding it with try/catch does not help...:)
try {
	def x='foo'
} catch(e) {
}
