#!/usr/bin/groovy

/*
	This is an example of adding a static method to a class

	Notes:
	- the syntax is Class.metaClass.static.methodName=closure
*/

// here is a class without a constructor...
class Book {
	static int count=0
	String title
	Book(title) {
		this.title=title
		Book.count++
	}
}
// lets create some books
b1=new Book('foo')
b2=new Book('bar')
// lets see if we can create instances of the book
try {
	def x=Book.getBookNumber()
} catch(GroovyRuntimeException e) {
	println 'yes, got a runtime exception since the class does not have a "getBookNumber" static method'
}
// lets add the static method...
Book.metaClass.static.getBookNumber={ return Book.count }
// now we can call our new static method...
println Book.getBookNumber()
