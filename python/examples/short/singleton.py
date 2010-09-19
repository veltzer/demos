#!/usr/bin/python

class A:
	theA=None
	constructed=False
	def __init__(self):
		if A.constructed:
			print "ERROR"
		else:
			print 'in A constructor'
			A.constructed=True
			# add construction code here

def getInstance():
	if A.theA==None:
		A.theA=A()
	return A.theA

a=A()
myA=getInstance()
print dir(myA)
