#!/usr/bin/python
class Book:
	num=0
	def __init__(self,price):
		self.__price=price
		Book.num+=1
	def printit(self):
		print 'price is',self.__price
	def setPrice(self,newprice):
		self.__price=newprice
def getNumBooks():
	return Book.num

b=Book(14)
b.printit()
b2=Book(13)
b2.printit()
print Book.num
print getNumBooks() 
