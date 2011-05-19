#!/usr/bin/groovy

/*
	Example showing default values for parameters in groovy...
*/

import org.codehaus.groovy.runtime.metaclass.MethodSelectionException

// simple example 
def say(String msg='hello') {
	println msg
}
say()
say('goodbye')

// parameters with defaults and parameters without... 
def multiSay(int num,String msg='hello') {
	for(x in 1..num) {
		println msg
	}
}
multiSay(2)
multiSay(3,'goodbye')

//lets try to forget a value for a function
// this is a RUNTIME exception because of the language is DYNAMIC
try {
	multiSay()
} catch(MethodSelectionException e) {
	println 'yes, could not find the method to invoke'+e
}

// default value for first parameter and not for second
def multiSay2(int num=5,String msg) {
	for(x in 1..num) {
		println msg
	}
}
// this next one actually finds the above method...
multiSay2('my msg')
// this is passing all arguments
multiSay2(8,'my msg2')
