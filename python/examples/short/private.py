#!/usr/bin/python

# example of a private value...

class Book:
	def printIt(self):
		print 'here is the book data'
		print 'price is',self.__price
		print 'finished printing the book'
	def setPrice(self,price):
		self.__price=price
	def getPrice(self):
		return self.__price
	def setName(self,name):
		self._name=name
	def getName(self):
		return self._name

b=Book()
b.setPrice(50)
b.setName("Lord of the Rings")
b.printIt()
# this next line produces a run time exception...
#print "price is ", b.__price
print dir(b)
b._Book__price=70
b.printIt()
print b._name
