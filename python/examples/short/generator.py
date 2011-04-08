#!/usr/bin/python

"""
This is an example of how to build a simple generator
"""

def my_reverse(data):
	for index in range(len(data)-1, -1, -1):
		yield data[index]

for char in my_reverse('golf'):
	print char

"""
Notice that 'my_reverse' is still recognized as a plain function and not
'generator' or something.
"""
print type(my_reverse)
