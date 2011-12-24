#!/usr/bin/groovy

/*
	This is an example of a Meta Object Protocol implemented in Groovy
*/

class MOPHandler {	
	def invokeMethod(String method, Object params) { 	
		println "MOPHandler was asked to invoke ${method}"
		if(params != null){
			params.each{ println "\twith parameter ${it}" }
		}
	}
	def getProperty(String property){
		println "MOPHandler was asked for property ${property}"
	}  
}
def hndler = new MOPHandler()
hndler.helloWorld()
hndler.createUser("Joe", 18, new Date())
hndler.name
