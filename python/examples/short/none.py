#!/usr/bin/python

"""
This example explores the None variable and NoneType in python.
"""

a=None

# lets print the type of None
# this type is special and is shared by no other type in the python type system.
print "type of None is", type(a)

# lets show that two nones are exactly the same
# they sort of "point to" the same place as far as the python "is" operator is concerned.
b=None
if a==b:
	print "yes , None==None"
if a is b:
	print "yes , None is None"

# lets show that None serves as 'False' as far as booleans are concerned.
if None:
	pass
else:
	print "yes, None is False as far as 'if' statements are concerned"

# lets build a bool from None and see that it is false
b=bool(None)
print "bool(None) is ",b

# lets try to build an int from a None and see that we fail.
# This is good behaviour since it protects you from accidentaly
# interpreting Nones as integers. Imagine an array storing both where
# you do not pay attention to checking if the value is None before doing
# some arithmetic with it.
try:
	i=int(None)
except TypeError as e:
	print "yes, got exception from convering None to int",e

# on the other hand, None is covertible to str (string). Watch out!
s=str(None)
print "str(None) has type",type(s),"and has value",s

# lets show that IT IS NOT 0 or the empry string or False as far as actual value.
# We show this by storing all of them as keys in a dictionary.
# Notice that False and 0 do step one over the other (not nice!).
h={}
h[None]="foo"
h[0]="bar"
h['']="zoo"
h[False]="fubar"
print h