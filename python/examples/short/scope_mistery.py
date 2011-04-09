#!/usr/bin/python

"""
Question: what does this function print ?

Answer: exception.
"""

def my_mistery_function():
	#global g
	print g
	g=17
	print g

g=4
my_mistery_function()
print g
