#!/usr/bin/groovy

/*
	This is an example of doing OO work with closures.
*/

def Person(name,age) {
	def getName = { name }
	def getAge = { age }
	def setName = { iname -> name=iname }
	def setAge = { iage -> age=iage }
	return [getName,getAge,setName,setAge]
}

def p1=Person('Frodo',73)
def p2=Person('Bilbo',111)
println p1[0]()
println p2[0]()
p1[2]('Gandalf')
p1[3](1456)
println p1[0]()
