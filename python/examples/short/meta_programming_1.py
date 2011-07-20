#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
class A:
	def __init__(self):
		self.myfield=7
	def my_method(self):
		print "Hello"

a=A()
print a.__dict__
print a.__class__
a.my_method()
print a.__dict__
print A.__dict__
