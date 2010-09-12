#!/usr/bin/python

class A:
	def __init__(self,val):
		self.privar=val
	def __init__(self):
		self.privar=5
	def foo(self):
		print self.privar,"hello"
	def foo(self,a):
		print self.privar,"hello",a

a=A()
try:
	a.foo()
except TypeError:
	print 'oops, got an error'
a.foo("mark")
