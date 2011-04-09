#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""
def create_func(l):
	def append_f(x):
		l.append(x)
	def print_f():
		print l
	def sum_f():
		return sum(l)
	return append_f,print_f,sum_f 

ap_f,pr_f,su_f=create_func([1,2,3])
ap_f(4)
pr_f()
print su_f()
ap_f(5)
pr_f()
print su_f()
