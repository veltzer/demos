#!/usr/bin/python

def make_mult_add_set(x):
	def mult(y):
		return x[0]*y
	def add(y):
		return x[0]+y
	def set(ix):
		x[0]=ix
	return (mult,add,set)

x=[3]
(func_mult,func_add,func_set)=make_mult_add_set(x)
#make_mult_add_set=None
(func_mult1,func_add1,func_set1)=make_mult_add_set(x)

print func_mult(7)
print func_mult1(7)
func_set(6)
print func_mult(7)
print func_mult1(7)
#print func_mult(7)
#print func_add(9)
#print func_mult.__closure__
#print func_add.__closure__
#print func_set.__closure__
