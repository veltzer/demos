#!/usr/bin/python

class A:
	def __getattr__(self,name):
		return name[:-1]

a=A()
dir(a)
print a.whatIsThis
print a.whatsGoingOn
print a.howManyAttributesDoesThisObjectHave
