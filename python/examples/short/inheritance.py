#!/usr/bin/python

class A:
	def __init__(self):
		print 'in A constructor'
		self.a=None
	def printA(self):
		print "A.a is ", self.a

class B(A):
	def __init__(self):
		self.b=10
		A.__init__(self)
		print 'in B constructor'

b=B()
b.printA()
print dir(b)
