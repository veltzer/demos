#!/usr/bin/groovy

class Person {
	String name
} 
class House {
	def sell() {
		println this
		"sell house"
	}
	def buy() {
		println this
		"buy house"
	}
}

// lets create a person
def p = new Person()
try {
	p.buyHouse()
} catch(MissingMethodException e) {
	println 'nope, I dont have a buyHouse method'
}
try {
	p.sellHouse()
} catch(MissingMethodException e) {
	println 'nope, I dont have a sellHouse method'
}
// lets borrow the 'buy' method using a house instance
def h = new House()
Person.metaClass.buyHouse = h.&buy
// the old person still doesn't have a 'buyHouse' method...
try {
	p.buyHouse()
} catch(MissingMethodException e) {
	println 'nope, I dont have a buyHouse method'
}
// lets create a new person
def p2=new Person()
assert "buy house" == p2.buyHouse()
