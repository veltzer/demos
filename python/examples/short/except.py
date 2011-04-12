#!/usr/bin/python

try:
	raise ValueError("hello")
except ValueError, e:
	print "in except",e
finally:
	print "finally is here"
