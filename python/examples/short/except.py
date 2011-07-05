#!/usr/bin/python

"""
	Mark Veltzer <mark@veltzer.net>
"""

try:
	raise ValueError("hello")
except ValueError, e:
	print "in except",e
finally:
	print "finally is here"
