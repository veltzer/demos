#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class A:
	a2=9
	def __init__(self):
		self.a=6
	def onlyAmethod():
		pass

class B(A):
	b2=11
	def __init__(self):
		A.__init__(self)
		self.b=10
	def onlyBmethod():
		pass

a=A()
b=B()
print dir(a)
print dir(b)
