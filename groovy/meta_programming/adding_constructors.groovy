#!/usr/bin/groovy

/*
	This is an example of adding a constructor to a class via the .metaClass
	ExpandoMetaClass instance

	Notes:
	- watch out for infinite recursion and stack overflows that can happen
	if you accidentaly call the constructor from within the newly added constructor
*/

// here is a class without a constructor...
class Book {
	String title
}
// lets see if we can create instances of the book
try {
	def newBook=new Book("The Stand")
} catch(GroovyRuntimeException e) {
	println 'yes, got a runtime exception since the class does not have a one argument constructor'
}
// lets add a constructor via the .metaClass...
Book.metaClass.constructor << { String title -> new Book(title:title) }
// lets create a book via the newly added constructor...
def b = new Book("The Stand")
// lets see that all is well...
println b.title
