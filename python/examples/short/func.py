#!/usr/bin/python

def square(x):
	return x**2

def mymap(f,list):
	ret=[]
	for x in list:
		ret.append(f(x))
	return ret

mymap(square,[1,2,3,4,5])
