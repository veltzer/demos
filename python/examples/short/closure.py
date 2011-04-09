#!/usr/bin/python

def make_funcs(l):
	def max_it():
		max=0
		for x in l:
			if x>max:
				max=x
		return max
	def min_it():
		min=10000
		for x in l:
			if x<min:
				min=x
		return min
	def value():
		return l
	return (max_it,min_it,value)

l=[1,2,3,4]
(ma,mi,va)=make_funcs(l)
l=None
print "max is ",ma()
print "min is ",mi()
print "va is ",va()
va()[3]=90
print "max is ",ma()
print "min is ",mi()
print "va is ",va()

ma=None
mi=None
va=None
