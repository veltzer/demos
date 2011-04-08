#!/usr/bin/python

def my_mistery_function():
	global g
	print g
	g=17
	print g

g=4
my_mistery_function()
print g
