#!/usr/bin/python

# This is quite an advanced example of doing meta programming in python.
# This exercise shows how to:
#	- how to add a method to a class
#	- how to add a method to an instance. 

from new import *
import pprint

class Person:
	def __init__(self,name,fname):
		self.name=name
		self.fname=fname
	def printMe(self):
		print self.name+" "+self.fname

p=Person("Mark","Veltzer");
p.printMe()

b=Person("James","Bond")

# lets define a function that looks like a method of person...
def secret_agent_print(self):
	print self.fname+", "+self.name+" "+self.fname

# lets add this method only to the 'b' instance...
b.printMe=instancemethod(secret_agent_print,b,Person)
print "Only James is a secret agent..."
p.printMe()
b.printMe()

# now everyone is a secret agent...
# both of these will work (2nd version is better...)
#b.__class__.printMe=secret_agent_print
Person.printMe=instancemethod(secret_agent_print,None,Person)
print "Now we are both secret agents..."
b.printMe()
p.printMe()

#lets add a method new method to the class

def fire_your_berreta(self):
	print self.name+" is firing!"
Person.fire=fire_your_berreta

# lets kill some people
print "Now we both have firing capabilities..."
p.fire()
b.fire()

print "Here is some debug info:"
pprint.pprint(b.__dict__)
pprint.pprint(b.__class__)
pprint.pprint(b.__class__.__dict__)