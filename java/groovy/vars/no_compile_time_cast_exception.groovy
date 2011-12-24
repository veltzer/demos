#!/usr/bin/groovy

/*
	This example shows that you cannot get a compile time class cast
	exception from Groovy even when using two typed variables that
	have no castring between them.
*/

class Car {
}

class Plane {
}

Car c=new Car()
Plane p=new Plane()

c=p
