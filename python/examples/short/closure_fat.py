#!/usr/bin/python

def create_func(l):
	def inner_func(x):
		l.append(x)
		print sum(l)
	return inner_func

inner=create_func([1,2,3])
inner(4)
inner(5)
inner(6)
