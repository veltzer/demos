#!/usr/bin/python

"""
An example exploring the many ways, right and wrong, to iterate
a dictionary in python.
"""

"""
	Mark Veltzer <mark@veltzer.net>
"""
h={}
h["keyone"]="valone"
h["keytwo"]="valtwo"

# this is the most efficient method
# each time you get BOTH a key AND value
for x in h.items():
	print x
	print "key is ",x[0]
	print "val is ",x[1]
# this is the same but you only get a key each time
for x in h:
	print x
	print "key is ",x
	print "val is ",h[x]
# this is less efficient (at least in some versions of python)
for x in h.keys():
	print x
	print "key is ",x
	print "val is ",h[x]
# this is TERRIBLE performance wise although to a novice it looks
# almost the same...
list=h.keys()
for x in list:
	print x
	print "key is ",x
	print "val is ",h[x]
