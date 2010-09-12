#!/usr/bin/python

class A:
	def __init__(self,val):
		self.privar=val
class B:
	def __init__(self,val):
		self.privar=val

a=A(6)
b=5
c=5.5
d="string"
e=B(8)
print type(a), type(b), type(c), type(d), type(e)
#print dir(a)
# this prints the class name for a
print a.__class__
print e.__class__
#h={}
#h[a]=a
