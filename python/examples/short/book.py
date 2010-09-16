#!/usr/bin/python
class Book:
	__price=0
	def printit(self):
		print 'price is',self.__price
	def setPrice(self,newprice):
		self.__price=newprice
	def __init__(self,price):
		self.__price=price
