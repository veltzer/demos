#!/usr/bin/groovy

/*
	This is an example of adding a constructor to a class via the .metaClass
	ExpandoMetaClass instance
*/

// here is a class without a constructor...
class Book {
	String title
}
// lets add a constructor via the .metaClass...
Book.metaClass.constructor << { String title -> new Book(title:title) }
// lets create a book via the newly added constructor...
def b = new Book("The Stand")
// lets see that all is well...
println b.title
