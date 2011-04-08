#!/usr/bin/python

"""
this is an example of implementing the python builtin 'map'
function in python.
"""
def my_map(f,seq):
	l=[]
	for x in seq:
		l.append(f(x))
	return l


print my_map(lambda x:x*x,range(10))
