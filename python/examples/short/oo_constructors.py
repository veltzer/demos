#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class A:
	def __init__():
		print 'in A constructor'

class B:
	def __init__(self):
		print 'in B constructor'
class C:
	def __init__(self,* args):
		print 'in C constructor'

try:
	a=A()
except TypeError:
	print 'oh, no. Cant construct an object. Must pass self'
b=B()
c=C()
