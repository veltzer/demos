#!/usr/bin/groovy

/*
	This is an example of doing OO work with closures.
	the idea is that multiple closures that return from
	a single method share the local data and therefor we
	have multiple code (methods) that share data which is
	what OO is all about.
*/

def Person(name,age) {
	def getName = { name }
	def getAge = { age }
	def setName = { iname -> name=iname }
	def setAge = { iage -> age=iage }
	return [
		'getName':getName,
		'getAge':getAge,
		'setName':setName,
		'setAge':setAge
	]
}

def p1=Person('Frodo',73)
def p2=Person('Bilbo',111)
println p1['getName']()
println p2['getName']()
p1['setName']('Gandalf')
p1['setAge'](1456)
println p1['getName']()
