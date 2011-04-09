#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class A:
	def __init__(self,val):
		print 'in A constructor'
		self.a=val
	def printA(self):
		print "A.a is ", self.a
	def printDict(self):
		print "dict is ", self.__dict__

a=A(7)
print dir(a)
a.printDict()
