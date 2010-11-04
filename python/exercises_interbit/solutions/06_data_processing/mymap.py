#!/usr/bin/python

# this is an example of how the 'map' function in python

def my_map(func,val_list):
	result=[]
	for value in val_list:
		result.append(func(value))
	return result

def square(x):
	return x*x

print my_map(square,xrange(2,8))
