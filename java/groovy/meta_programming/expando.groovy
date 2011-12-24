#!/usr/bin/groovy

/*
	This is an example of the ExpandoMetaClass class

	Here we add a method to the String class that swaps case...
*/

String s='fooBAR'
String s2='blaBla'
//String.metaClass.swapCase = {->
s.metaClass.swapCase = {->
	def sb = new StringBuffer()
	delegate.each {
		sb << (Character.isUpperCase(it as char) ? Character.toLowerCase(it as char) : 
			Character.toUpperCase(it as char))
	}
	sb.toString()
}
println s.swapCase()
//println s2.swapCase()
