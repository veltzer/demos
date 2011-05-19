#!/usr/bin/groovy

/*
	An example exploring protection in groovy

	Notes:
	- this shows that attributes are public by default
*/

class Book {
	def title
	def printMe() {
		println 'book title is '+this.title
	}
}
// lets try to create a book and directly access the 'title' attribute...
def b=new Book()
b.title='foo'
b.printMe()
// auto generated getter is also available (public)
println 'the title of the book is '+b.getTitle()
// you can also use the auto generated setter (public)
b.setTitle('bar')
b.printMe()
